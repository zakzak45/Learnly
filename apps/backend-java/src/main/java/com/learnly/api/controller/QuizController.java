package com.learnly.api.controller;

import com.learnly.api.dto.QuizQuestionResponse;
import com.learnly.api.dto.QuizResultResponse;
import com.learnly.api.dto.QuizSubmissionRequest;
import com.learnly.api.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<QuizQuestionResponse> getQuestions() {
        return quizService.getQuestions();
    }

    @PostMapping("/submit")
    public QuizResultResponse submit(@Valid @RequestBody QuizSubmissionRequest request) {
        return quizService.submit(request);
    }
}
