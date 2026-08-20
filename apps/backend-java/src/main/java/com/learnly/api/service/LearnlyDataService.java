package com.learnly.api.service;

import com.learnly.api.model.CareerPath;
import com.learnly.api.model.LearningTrack;
import com.learnly.api.model.Opportunity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnlyDataService {

    public List<LearningTrack> tracks() {
        return List.of(
                new LearningTrack(
                        "matric",
                        "Matric Pathway",
                        "School leavers and gap-year learners",
                        List.of(
                                "Career matching by interests and school subjects",
                                "Bursary and TVET navigation support",
                                "Personal study planning for independent learning"
                        )
                ),
                new LearningTrack(
                        "university",
                        "University Pathway",
                        "Undergraduate and final-year students",
                        List.of(
                                "Role-based skills roadmaps",
                                "CV, LinkedIn, and portfolio readiness",
                                "Interview and workplace communication practice"
                        )
                )
        );
    }

    public List<CareerPath> careers() {
        return List.of(
                new CareerPath(
                        "software-developer",
                        "Software Developer",
                        "High",
                        List.of("Fintech", "Retail", "Telecom"),
                        List.of("HTML", "JavaScript", "Git", "Problem Solving")
                ),
                new CareerPath(
                        "data-analyst",
                        "Data Analyst",
                        "Growing",
                        List.of("Banking", "Logistics", "Public Sector"),
                        List.of("Excel", "SQL", "Data Visualization", "Storytelling")
                ),
                new CareerPath(
                        "ux-designer",
                        "UX Designer",
                        "Strong",
                        List.of("Digital Products", "EdTech", "HealthTech"),
                        List.of("User Research", "Figma", "Wireframing", "Accessibility")
                )
        );
    }

    public List<Opportunity> opportunities() {
        return List.of(
                new Opportunity(
                        "graduate-programmes",
                        "Graduate Programmes",
                        "Track opening periods and required documents early each year."
                ),
                new Opportunity(
                        "learnerships",
                        "Learnerships",
                        "Use your profile to match entry requirements by province."
                ),
                new Opportunity(
                        "internships",
                        "Internships",
                        "Apply with a role-specific CV and practical project evidence."
                )
        );
    }
}
