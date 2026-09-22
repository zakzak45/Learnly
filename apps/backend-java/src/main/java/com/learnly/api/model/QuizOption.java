package com.learnly.api.model;

import java.util.List;

public class QuizOption {

    private String id;
    private String text;
    private List<String> careerTags;

    public QuizOption() {}

    public QuizOption(String id, String text, List<String> careerTags) {
        this.id = id;
        this.text = text;
        this.careerTags = careerTags;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public List<String> getCareerTags() { return careerTags; }
    public void setCareerTags(List<String> careerTags) { this.careerTags = careerTags; }
}
