package com.learnly.api.controller;

import com.learnly.api.dto.CourseRequest;
import com.learnly.api.model.Course;
import com.learnly.api.model.Enrollment;
import com.learnly.api.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> list() {
        return courseService.listPublished();
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable String id) {
        return courseService.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Course create(@Valid @RequestBody CourseRequest request) {
        return courseService.create(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Course update(@PathVariable String id, @Valid @RequestBody CourseRequest request) {
        return courseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id) {
        courseService.delete(id);
    }

    @PostMapping("/{id}/enroll")
    public Enrollment enroll(@PathVariable String id, Authentication auth) {
        return courseService.enroll(auth.getName(), id);
    }

    @GetMapping("/my-enrollments")
    public List<Enrollment> myEnrollments(Authentication auth) {
        return courseService.myEnrollments(auth.getName());
    }

    @PutMapping("/{id}/modules/{moduleIndex}/complete")
    public Enrollment completeModule(@PathVariable String id, @PathVariable int moduleIndex, Authentication auth) {
        return courseService.completeModule(auth.getName(), id, moduleIndex);
    }
}
