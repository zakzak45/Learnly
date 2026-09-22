package com.learnly.api.dto;

import com.learnly.api.model.CareerPath;

import java.util.List;

public record QuizResultResponse(CareerPath topMatch, List<CareerPath> otherMatches) {}
