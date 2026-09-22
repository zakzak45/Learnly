package com.learnly.api.service;

import com.learnly.api.dto.QuizOptionResponse;
import com.learnly.api.dto.QuizQuestionResponse;
import com.learnly.api.dto.QuizResultResponse;
import com.learnly.api.dto.QuizSubmissionRequest;
import com.learnly.api.model.CareerPath;
import com.learnly.api.model.QuizOption;
import com.learnly.api.repository.CareerPathRepository;
import com.learnly.api.repository.QuizQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final CareerPathRepository careerPathRepository;

    public QuizService(QuizQuestionRepository quizQuestionRepository, CareerPathRepository careerPathRepository) {
        this.quizQuestionRepository = quizQuestionRepository;
        this.careerPathRepository = careerPathRepository;
    }

    public List<QuizQuestionResponse> getQuestions() {
        return quizQuestionRepository.findAll().stream()
                .map(q -> new QuizQuestionResponse(
                        q.getId(),
                        q.getText(),
                        q.getOptions().stream()
                                .map(o -> new QuizOptionResponse(o.getId(), o.getText()))
                                .toList()))
                .toList();
    }

    public QuizResultResponse submit(QuizSubmissionRequest request) {
        Map<String, Integer> tagCounts = new HashMap<>();

        List<QuizOption> allOptions = quizQuestionRepository.findAll().stream()
                .flatMap(q -> q.getOptions().stream())
                .toList();

        for (String selectedId : request.selectedOptionIds()) {
            allOptions.stream()
                    .filter(o -> o.getId().equals(selectedId))
                    .findFirst()
                    .ifPresent(o -> o.getCareerTags().forEach(tag ->
                            tagCounts.merge(tag, 1, Integer::sum)));
        }

        List<String> rankedCareerIds = tagCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();

        if (rankedCareerIds.isEmpty()) {
            throw new IllegalArgumentException("No valid answers submitted");
        }

        Map<String, CareerPath> careersById = careerPathRepository.findAll().stream()
                .collect(Collectors.toMap(CareerPath::getCareerKey, c -> c));

        CareerPath topMatch = careersById.get(rankedCareerIds.get(0));
        List<CareerPath> others = rankedCareerIds.stream()
                .skip(1)
                .map(careersById::get)
                .filter(Objects::nonNull)
                .toList();

        return new QuizResultResponse(topMatch, others);
    }
}
