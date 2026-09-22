package com.learnly.api.controller;

import com.learnly.api.dto.PendingReviewResponse;
import com.learnly.api.model.VerificationStatus;
import com.learnly.api.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reviews")
@PreAuthorize("hasRole('ADMIN')")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<PendingReviewResponse> listPending() {
        return reviewService.listPending();
    }

    @PutMapping("/{enrollmentId}/modules/{moduleIndex}/verify")
    public void verify(@PathVariable String enrollmentId, @PathVariable int moduleIndex) {
        reviewService.decide(enrollmentId, moduleIndex, VerificationStatus.VERIFIED);
    }

    @PutMapping("/{enrollmentId}/modules/{moduleIndex}/reject")
    public void reject(@PathVariable String enrollmentId, @PathVariable int moduleIndex) {
        reviewService.decide(enrollmentId, moduleIndex, VerificationStatus.REJECTED);
    }
}
