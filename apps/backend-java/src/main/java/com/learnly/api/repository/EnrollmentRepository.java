package com.learnly.api.repository;

import com.learnly.api.model.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByStudentEmail(String studentEmail);
    Optional<Enrollment> findByStudentEmailAndCourseId(String studentEmail, String courseId);
}
