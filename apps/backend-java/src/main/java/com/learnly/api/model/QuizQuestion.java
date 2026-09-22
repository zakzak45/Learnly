package com.learnly.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "quiz_questions")
public class QuizQuestion {

    @Id
    private String id;
    private String text;
    private List<QuizOption> options;

    public QuizQuestion() {}

    public QuizQuestion(String text, List<QuizOption> options) {
        this.text = text;
        this.options = options;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public List<QuizOption> getOptions() { return options; }
    public void setOptions(List<QuizOption> options) { this.options = options; }
}
