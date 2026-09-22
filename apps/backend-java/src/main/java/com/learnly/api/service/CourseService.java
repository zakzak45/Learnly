package com.learnly.api.service;

import com.learnly.api.dto.CourseRequest;
import com.learnly.api.model.Course;
import com.learnly.api.model.Enrollment;
import com.learnly.api.model.ModuleCompletion;
import com.learnly.api.model.VerificationStatus;
import com.learnly.api.repository.CourseRepository;
import com.learnly.api.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final Random random = new Random();

    @Value("${learnly.verification.spot-check-rate}")
    private double spotCheckRate; // e.g. 0.2 = 20% chance a completion gets flagged for review

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Course> listPublished() {
        return courseRepository.findByPublishedTrue();
    }

    public Course get(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    public Course create(CourseRequest request) {
        Course course = new Course(request.title(), request.description(), request.category(),
                request.modules(), request.published());
        return courseRepository.save(course);
    }

    public Course update(String id, CourseRequest request) {
        Course course = get(id);
        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setCategory(request.category());
        course.setModules(request.modules());
        course.setPublished(request.published());
        return courseRepository.save(course);
    }

    public void delete(String id) {
        courseRepository.deleteById(id);
    }

    public Enrollment enroll(String studentEmail, String courseId) {
        get(courseId); // 404s if course doesn't exist
        return enrollmentRepository.findByStudentEmailAndCourseId(studentEmail, courseId)
                .orElseGet(() -> enrollmentRepository.save(new Enrollment(studentEmail, courseId)));
    }

    public List<Enrollment> myEnrollments(String studentEmail) {
        return enrollmentRepository.findByStudentEmail(studentEmail);
    }

    public Enrollment completeModule(String studentEmail, String courseId, int moduleIndex) {
        Enrollment enrollment = enrollmentRepository.findByStudentEmailAndCourseId(studentEmail, courseId)
                .orElseThrow(() -> new IllegalArgumentException("Not enrolled in this course"));

        boolean alreadyRecorded = enrollment.getCompletions().stream()
                .anyMatch(c -> c.getModuleIndex() == moduleIndex);

        if (!alreadyRecorded) {
            VerificationStatus status = random.nextDouble() < spotCheckRate
                    ? VerificationStatus.PENDING_REVIEW
                    : VerificationStatus.SELF_REPORTED;
            enrollment.getCompletions().add(new ModuleCompletion(moduleIndex, status));
        }

        return enrollmentRepository.save(enrollment);
    }
}
