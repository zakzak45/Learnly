package com.learnly.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "enrollments")
public class Enrollment {

    @Id
    private String id;
    private String studentEmail;
    private String courseId;
    private Instant enrolledAt;
    private List<ModuleCompletion> completions = new ArrayList<>();

    public Enrollment() {}

    public Enrollment(String studentEmail, String courseId) {
        this.studentEmail = studentEmail;
        this.courseId = courseId;
        this.enrolledAt = Instant.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public Instant getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(Instant enrolledAt) { this.enrolledAt = enrolledAt; }
    public List<ModuleCompletion> getCompletions() { return completions; }
    public void setCompletions(List<ModuleCompletion> completions) { this.completions = completions; }
}
