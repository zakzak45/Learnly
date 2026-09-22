package com.learnly.api.service;

import com.learnly.api.dto.PendingReviewResponse;
import com.learnly.api.model.Course;
import com.learnly.api.model.Enrollment;
import com.learnly.api.model.ModuleCompletion;
import com.learnly.api.model.VerificationStatus;
import com.learnly.api.repository.CourseRepository;
import com.learnly.api.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public ReviewService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    public List<PendingReviewResponse> listPending() {
        return enrollmentRepository.findAll().stream()
                .flatMap(enrollment -> enrollment.getCompletions().stream()
                        .filter(c -> c.getStatus() == VerificationStatus.PENDING_REVIEW)
                        .map(c -> toResponse(enrollment, c)))
                .toList();
    }

    public void decide(String enrollmentId, int moduleIndex, VerificationStatus decision) {
        if (decision != VerificationStatus.VERIFIED && decision != VerificationStatus.REJECTED) {
            throw new IllegalArgumentException("Decision must be VERIFIED or REJECTED");
        }

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        ModuleCompletion completion = enrollment.getCompletions().stream()
                .filter(c -> c.getModuleIndex() == moduleIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No completion recorded for that module"));

        completion.setStatus(decision);
        enrollmentRepository.save(enrollment);
    }

    private PendingReviewResponse toResponse(Enrollment enrollment, ModuleCompletion completion) {
        Course course = courseRepository.findById(enrollment.getCourseId()).orElse(null);
        String courseTitle = course != null ? course.getTitle() : "Unknown course";
        String moduleTitle = (course != null && course.getModules() != null
                && completion.getModuleIndex() < course.getModules().size())
                ? course.getModules().get(completion.getModuleIndex()).getTitle()
                : "Unknown module";

        return new PendingReviewResponse(
                enrollment.getId(),
                enrollment.getStudentEmail(),
                enrollment.getCourseId(),
                courseTitle,
                completion.getModuleIndex(),
                moduleTitle
        );
    }
}
