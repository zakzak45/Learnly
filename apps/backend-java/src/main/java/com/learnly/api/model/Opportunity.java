package com.learnly.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "opportunities")
public class Opportunity {

    @Id
    private String id;
    private String type;
    private String guidance;

    public Opportunity() {}

    public Opportunity(String type, String guidance) {
        this.type = type;
        this.guidance = guidance;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getGuidance() { return guidance; }
    public void setGuidance(String guidance) { this.guidance = guidance; }
}
