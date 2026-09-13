package com.learnly.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "learning_tracks")
public class LearningTrack {

    @Id
    private String id;
    private String trackKey;
    private String title;
    private String audience;
    private List<String> outcomes;

    public LearningTrack() {}

    public LearningTrack(String trackKey, String title, String audience, List<String> outcomes) {
        this.trackKey = trackKey;
        this.title = title;
        this.audience = audience;
        this.outcomes = outcomes;
    }

    public String getId() { return id; }
    public String getTrackKey() { return trackKey; }
    public String getTitle() { return title; }
    public String getAudience() { return audience; }
    public List<String> getOutcomes() { return outcomes; }
    public void setId(String id) { this.id = id; }
    public void setTrackKey(String trackKey) { this.trackKey = trackKey; }
    public void setTitle(String title) { this.title = title; }
    public void setAudience(String audience) { this.audience = audience; }
    public void setOutcomes(List<String> outcomes) { this.outcomes = outcomes; }
}