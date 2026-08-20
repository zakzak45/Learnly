package com.learnly.api.controller;

import com.learnly.api.model.CareerPath;
import com.learnly.api.model.LearningTrack;
import com.learnly.api.model.Opportunity;
import com.learnly.api.service.LearnlyDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LearnlyController {

    private final LearnlyDataService dataService;

    public LearnlyController(LearnlyDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok", "service", "learnly-spring-api");
    }

    @GetMapping("/tracks")
    public List<LearningTrack> tracks() {
        return dataService.tracks();
    }

    @GetMapping("/careers")
    public List<CareerPath> careers() {
        return dataService.careers();
    }

    @GetMapping("/opportunities")
    public List<Opportunity> opportunities() {
        return dataService.opportunities();
    }
}
