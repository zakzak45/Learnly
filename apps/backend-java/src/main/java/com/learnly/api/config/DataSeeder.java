package com.learnly.api.config;

import com.learnly.api.model.CareerPath;
import com.learnly.api.model.LearningTrack;
import com.learnly.api.model.Opportunity;
import com.learnly.api.repository.CareerPathRepository;
import com.learnly.api.repository.LearningTrackRepository;
import com.learnly.api.repository.OpportunityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final LearningTrackRepository trackRepository;
    private final CareerPathRepository careerRepository;
    private final OpportunityRepository opportunityRepository;

    public DataSeeder(LearningTrackRepository trackRepository,
                      CareerPathRepository careerRepository,
                      OpportunityRepository opportunityRepository) {
        this.trackRepository = trackRepository;
        this.careerRepository = careerRepository;
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    public void run(String... args) {
        if (trackRepository.count() == 0) {
            trackRepository.saveAll(List.of(
                    new LearningTrack("matric", "Matric Pathway", "School leavers and gap-year learners",
                            List.of("Career matching by interests and school subjects",
                                    "Bursary and TVET navigation support",
                                    "Personal study planning for independent learning")),
                    new LearningTrack("university", "University Pathway", "Undergraduate and final-year students",
                            List.of("Role-based skills roadmaps",
                                    "CV, LinkedIn, and portfolio readiness",
                                    "Interview and workplace communication practice"))
            ));
        }
    }
}