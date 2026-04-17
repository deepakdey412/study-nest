package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.dao.QuestionRepository;
import com.deepak.Study_Nest.dto.QuestionDto;
import com.deepak.Study_Nest.entity.Question;
import com.deepak.Study_Nest.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public List<QuestionDto> getQuestionsByModuleId(Long moduleId, boolean includeAnswers) {
        List<Question> questions = questionRepository.findByModuleId(moduleId);
        
        if (questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for module id: " + moduleId);
        }

        return questions.stream()
                .map(q -> convertToDto(q, includeAnswers))
                .collect(Collectors.toList());
    }

    private QuestionDto convertToDto(Question question, boolean includeAnswers) {
        QuestionDto.QuestionDtoBuilder builder = QuestionDto.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4());

        if (includeAnswers) {
            builder.correctAnswer(question.getCorrectAnswer());
        }

        return builder.build();
    }
}
