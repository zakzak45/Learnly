package com.learnly.api.dto;

public record EmployabilityResponse(
        int score,
        int coursesEnrolled,
        int modulesCompleted,
        int totalModules,
        String message
) {}
