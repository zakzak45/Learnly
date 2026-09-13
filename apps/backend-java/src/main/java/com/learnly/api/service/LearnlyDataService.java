package com.learnly.api.service;

import com.learnly.api.model.CareerPath;
import com.learnly.api.model.LearningTrack;
import com.learnly.api.model.Opportunity;
import com.learnly.api.repository.CareerPathRepository;
import com.learnly.api.repository.LearningTrackRepository;
import com.learnly.api.repository.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnlyDataService {

    private final LearningTrackRepository trackRepository;
    private final CareerPathRepository careerRepository;
    private final OpportunityRepository opportunityRepository;

    public LearnlyDataService(LearningTrackRepository trackRepository,
                              CareerPathRepository careerRepository,
                              OpportunityRepository opportunityRepository) {
        this.trackRepository = trackRepository;
        this.careerRepository = careerRepository;
        this.opportunityRepository = opportunityRepository;
    }

    public List<LearningTrack> tracks() { return trackRepository.findAll(); }
    public List<CareerPath> careers() { return careerRepository.findAll(); }
    public List<Opportunity> opportunities() { return opportunityRepository.findAll(); }
}