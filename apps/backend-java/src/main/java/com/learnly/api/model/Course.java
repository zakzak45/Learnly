package com.learnly.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "courses")
public class Course {

    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private List<Module> modules;
    private boolean published;

    public Course() {}

    public Course(String title, String description, String category, List<Module> modules, boolean published) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.modules = modules;
        this.published = published;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public List<Module> getModules() { return modules; }
    public void setModules(List<Module> modules) { this.modules = modules; }
    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }
}
