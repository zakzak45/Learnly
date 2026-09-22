package com.learnly.api.dto;

import java.util.List;

public record QuizQuestionResponse(String id, String text, List<QuizOptionResponse> options) {}
