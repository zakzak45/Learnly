package com.learnly.api.dto;

public record PendingReviewResponse(
        String enrollmentId,
        String studentEmail,
        String courseId,
        String courseTitle,
        int moduleIndex,
        String moduleTitle
) {}
