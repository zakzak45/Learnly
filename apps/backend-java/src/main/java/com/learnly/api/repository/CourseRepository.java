package com.learnly.api.repository;

import com.learnly.api.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByPublishedTrue();
}
