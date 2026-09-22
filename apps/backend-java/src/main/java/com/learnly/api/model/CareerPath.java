package com.learnly.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "career_paths")
public class CareerPath {

    @Id
    private String id;
    private String careerKey;
    private String name;
    private String demand;
    private List<String> sectors;
    private List<String> starterSkills;

    public CareerPath() {}

    public CareerPath(String careerKey, String name, String demand, List<String> sectors, List<String> starterSkills) {
        this.careerKey = careerKey;
        this.name = name;
        this.demand = demand;
        this.sectors = sectors;
        this.starterSkills = starterSkills;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCareerKey() { return careerKey; }
    public void setCareerKey(String careerKey) { this.careerKey = careerKey; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDemand() { return demand; }
    public void setDemand(String demand) { this.demand = demand; }
    public List<String> getSectors() { return sectors; }
    public void setSectors(List<String> sectors) { this.sectors = sectors; }
    public List<String> getStarterSkills() { return starterSkills; }
    public void setStarterSkills(List<String> starterSkills) { this.starterSkills = starterSkills; }
}
