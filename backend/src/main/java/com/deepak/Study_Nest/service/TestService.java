package com.deepak.Study_Nest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.Study_Nest.dao.ModuleRepository;
import com.deepak.Study_Nest.dao.QuestionRepository;
import com.deepak.Study_Nest.dao.StudentRepository;
import com.deepak.Study_Nest.dao.StudentResultRepository;
import com.deepak.Study_Nest.dto.TestResultDto;
import com.deepak.Study_Nest.dto.TestSubmissionDto;
import com.deepak.Study_Nest.entity.Module;
import com.deepak.Study_Nest.entity.Question;
import com.deepak.Study_Nest.entity.Student;
import com.deepak.Study_Nest.entity.StudentResult;
import com.deepak.Study_Nest.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;
    private final QuestionRepository questionRepository;
    private final StudentResultRepository resultRepository;

    @Transactional
    public TestResultDto submitTest(String email, TestSubmissionDto submission) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Module module = moduleRepository.findById(submission.getModuleId())
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

        List<Question> questions = questionRepository.findByModuleId(submission.getModuleId());
        
        if (questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for this module");
        }

        // Calculate score
        int correctAnswers = 0;
        Map<Long, String> answers = submission.getAnswers();

        for (Question question : questions) {
            String submittedAnswer = answers.get(question.getId());
            if (submittedAnswer != null && submittedAnswer.equals(question.getCorrectAnswer())) {
                correctAnswers++;
            }
        }

        int totalQuestions = questions.size();
        BigDecimal percentage = BigDecimal.valueOf(correctAnswers)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(totalQuestions), 2, RoundingMode.HALF_UP);

        String status = percentage.compareTo(BigDecimal.valueOf(40)) >= 0 ? "PASSED" : "FAILED";

        // Save or update result
        StudentResult result = resultRepository
                .findByStudentIdAndModuleId(student.getId(), module.getId())
                .orElse(StudentResult.builder()
                        .student(student)
                        .module(module)
                        .build());

        result.setScore(correctAnswers);
        result.setTotalQuestions(totalQuestions);
        result.setPercentage(percentage);
        result.setStatus(status);

        result = resultRepository.save(result);

        log.info("Test submitted for student: {} on module: {} - Score: {}/{}", 
                email, module.getName(), correctAnswers, totalQuestions);

        return TestResultDto.builder()
                .id(result.getId())
                .moduleName(module.getName())
                .semester(module.getSemester())
                .score(correctAnswers)
                .correctAnswers(correctAnswers)
                .totalQuestions(totalQuestions)
                .percentage(percentage)
                .status(status)
                .passed(status.equals("PASSED"))
                .completedAt(result.getCompletedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public List<TestResultDto> getStudentResults(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return resultRepository.findByStudentId(student.getId()).stream()
                .map(result -> TestResultDto.builder()
                        .id(result.getId())
                        .moduleName(result.getModule().getName())
                        .score(result.getScore())
                        .totalQuestions(result.getTotalQuestions())
                        .percentage(result.getPercentage())
                        .status(result.getStatus())
                        .completedAt(result.getCompletedAt())
                        .semester(result.getModule().getSemester())
                        .correctAnswers(result.getScore())
                        .passed(result.getStatus().equals("PASSED"))
                        .build())
                .collect(Collectors.toList());
    }

    public Map<Long, Long> getQuestionCountsByModule() {
        List<Question> allQuestions = questionRepository.findAll();
        return allQuestions.stream()
                .collect(Collectors.groupingBy(
                        q -> q.getModule().getId(),
                        Collectors.counting()
                ));
    }
}
