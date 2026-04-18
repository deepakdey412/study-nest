package com.deepak.Study_Nest.controller;

import com.deepak.Study_Nest.dao.ModuleRepository;
import com.deepak.Study_Nest.dao.QuestionRepository;
import com.deepak.Study_Nest.entity.Module;
import com.deepak.Study_Nest.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/test-answers")
@RequiredArgsConstructor
public class TestAnswerController {

    private final QuestionRepository questionRepository;
    private final ModuleRepository moduleRepository;

    @GetMapping("/semester/{semester}")
    public Map<String, Object> getAnswersForSemester(@PathVariable Integer semester) {
        List<Module> modules = moduleRepository.findBySemester(semester);
        
        Map<String, Object> result = new HashMap<>();
        result.put("semester", semester);
        
        List<Map<String, Object>> moduleAnswers = new ArrayList<>();
        
        for (Module module : modules) {
            List<Question> questions = questionRepository.findByModuleId(module.getId());
            
            Map<String, Object> moduleData = new HashMap<>();
            moduleData.put("moduleId", module.getId());
            moduleData.put("moduleName", module.getName());
            
            List<Map<String, String>> questionAnswers = new ArrayList<>();
            
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                Map<String, String> qa = new HashMap<>();
                qa.put("questionNumber", String.valueOf(i + 1));
                qa.put("question", q.getQuestionText());
                qa.put("correctAnswer", q.getCorrectAnswer());
                
                // Get the actual text of correct answer
                String correctText = "";
                switch (q.getCorrectAnswer()) {
                    case "option1":
                        correctText = "A: " + q.getOption1();
                        break;
                    case "option2":
                        correctText = "B: " + q.getOption2();
                        break;
                    case "option3":
                        correctText = "C: " + q.getOption3();
                        break;
                    case "option4":
                        correctText = "D: " + q.getOption4();
                        break;
                }
                qa.put("correctAnswerText", correctText);
                
                questionAnswers.add(qa);
            }
            
            moduleData.put("answers", questionAnswers);
            moduleAnswers.add(moduleData);
        }
        
        result.put("modules", moduleAnswers);
        return result;
    }
}
