package com.learnly.api.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record QuizSubmissionRequest(@NotEmpty List<String> selectedOptionIds) {}
