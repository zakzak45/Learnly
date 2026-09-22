package com.learnly.api.service;

import com.learnly.api.dto.EmployabilityResponse;
import com.learnly.api.model.Course;
import com.learnly.api.model.Enrollment;
import com.learnly.api.model.ModuleCompletion;
import com.learnly.api.model.VerificationStatus;
import com.learnly.api.repository.CourseRepository;
import com.learnly.api.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployabilityService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    // Verified work counts in full; self-reported/pending counts, but at a
    // discount — this is what makes "verification" mean something instead
    // of being decorative.
    private static final double VERIFIED_WEIGHT = 1.0;
    private static final double UNVERIFIED_WEIGHT = 0.7;

    public EmployabilityService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    public EmployabilityResponse calculate(String studentEmail) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentEmail(studentEmail);

        int baseScore = 20;
        int breadthScore = Math.min(enrollments.size() * 10, 40);

        Map<String, Course> coursesById = courseRepository.findAllById(
                enrollments.stream().map(Enrollment::getCourseId).toList()
        ).stream().collect(Collectors.toMap(Course::getId, c -> c));

        int totalModules = 0;
        double weightedCompleted = 0;
        int verifiedCount = 0;
        int unverifiedCount = 0;

        for (Enrollment e : enrollments) {
            Course course = coursesById.get(e.getCourseId());
            if (course != null && course.getModules() != null) {
                totalModules += course.getModules().size();

                for (ModuleCompletion completion : e.getCompletions()) {
                    if (completion.getStatus() == VerificationStatus.REJECTED) {
                        continue; // rejected work doesn't count at all
                    }
                    if (completion.getStatus() == VerificationStatus.VERIFIED) {
                        weightedCompleted += VERIFIED_WEIGHT;
                        verifiedCount++;
                    } else {
                        weightedCompleted += UNVERIFIED_WEIGHT;
                        unverifiedCount++;
                    }
                }
            }
        }

        int depthScore = totalModules == 0 ? 0 : (int) Math.min(40, (weightedCompleted * 40.0) / totalModules);
        int score = Math.min(100, baseScore + breadthScore + depthScore);

        String message = score < 40
                ? "Enroll in a course and complete a few modules to boost your readiness."
                : verifiedCount == 0
                ? "Good progress — get some of your completed work verified to raise your standing further."
                : "Strong readiness profile, with verified work behind it.";

        return new EmployabilityResponse(score, enrollments.size(), verifiedCount + unverifiedCount, totalModules, message);
    }
}
