package com.learnly.api.config;

import com.learnly.api.model.CareerPath;
import com.learnly.api.model.Course;
import com.learnly.api.model.LearningTrack;
import com.learnly.api.model.Module;
import com.learnly.api.model.Opportunity;
import com.learnly.api.model.Role;
import com.learnly.api.model.User;
import com.learnly.api.repository.CareerPathRepository;
import com.learnly.api.repository.CourseRepository;
import com.learnly.api.repository.LearningTrackRepository;
import com.learnly.api.repository.OpportunityRepository;
import com.learnly.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final LearningTrackRepository trackRepository;
    private final CareerPathRepository careerRepository;
    private final OpportunityRepository opportunityRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${learnly.admin.email}")
    private String adminEmail;

    @Value("${learnly.admin.password}")
    private String adminPassword;

    @Value("${learnly.admin.firstName}")
    private String adminFirstName;

    @Value("${learnly.admin.lastName}")
    private String adminLastName;

    public DataSeeder(LearningTrackRepository trackRepository,
                      CareerPathRepository careerRepository,
                      OpportunityRepository opportunityRepository,
                      CourseRepository courseRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.trackRepository = trackRepository;
        this.careerRepository = careerRepository;
        this.opportunityRepository = opportunityRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedAdmin();
        seedTracks();
        seedCareers();
        seedOpportunities();
        seedCourses();
    }

    //Backend development
    //UX/UI Design
    //CyberSecurity Basics
    //ML Basics and Intro
    //Software Development
    //Web Development... With prerequisites of the front and backend dev
    //Digital Entrepreneurship
    //AI in the emerging market(How to use AI)
    //Excel Spreadsheets & Accounting
    private void seedCourses() {
        if (courseRepository.count() == 0) {
            courseRepository.saveAll(List.of(
                    new Course(
                            "Frontend Development Fundamentals",
                            "A curated path through free resources covering HTML, CSS, and JavaScript basics.",
                            "software-developer",
                            List.of(
                                    new Module(
                                            "Responsive Web Design",
                                            "HTML and CSS fundamentals, flexbox, and grid." ,
                                            "https://www.freecodecamp.org/learn/2022/responsive-web-design/",
                                            "freeCodeCamp",
                                            1
                                    ),
                                    new Module(
                                            "Advanced HTML & Css Course",
                                            "Go beyond convention UI designs. Not make it look like a simple bootcamp project ",
                                            "https://css-tricks.com/guides/",
                                            " CSS-Tricks",
                                            2
                                    ),
                                    new Module(
                                            "CSS Animations & Responsiveness",
                                            "Go beyond convention UI designs. Not make it look like a simple bootcamp project ",
                                            "https://css-tricks.com/guides/",
                                            " CSS-Tricks",
                                            3
                                    ),
                                    new Module(
                                            "JavaScript Algorithms and Data Structures",
                                            "Core JavaScript syntax, functions, and problem solving.",
                                            "https://www.freecodecamp.org/learn/javascript-algorithms-and-data-structures/",
                                            "freeCodeCamp",
                                            4
                                    ),
                                    new Module(
                                            "JavaScript intro in the world of the web",
                                            "Now get into the good stuff, some stuff will be repeated but that's how it sticks. Be sure to enjoy the side projects",
                                            "https://www.theodinproject.com/paths/full-stack-javascript/courses/javascripthttps://javascript.info/",
                                            "The Odin project",
                                            5
                                    ),
                                    new Module(
                                            "JavaScript Deep Dive",
                                            "Learn pasts the basics of your average programmer.",
                                            "https://javascript.info/",
                                            "The Modern JavaScript Tutorial",
                                            6
                                    ),
                                    new Module(
                                            "React Introduction",
                                            "An introduction to the free, open-source javascript library known as react, and why its worth your time",
                                            "https://youtu.be/V1PxgjIhTw0",
                                            "Code-Go video",
                                            7
                                    ),
                                    new Module(
                                            "React Course",
                                            "Now to get into the good stuff and learn react and take your skills to the next level, Projects?, yep there are a few. Have fun!",
                                            "https://www.theodinproject.com/paths/full-stack-javascript/courses/react",
                                            "The Odin Project",
                                            8
                                    ),
                                    new Module(
                                            "Reading Docs",
                                            "A programmer should have the capacity to gp beyond the bootcamp and learn the latest additions to a technology and to start have fun going through the react" +
                                                    "website and see if there is anything new you could learn or add to your current skill set.",
                                            "https://react.dev/",
                                            "React Home",
                                            9
                                    ),
                                    new Module(
                                            "Project",
                                            "N/A",
                                            "N/A",
                                            "N/A",
                                            10
                                    )
                            ),
                            true
                    ),
                    new Course(
                            "Data Analysis Starter Path",
                            "A curated path through free resources for foundational data analysis skills.",
                            "data-analyst",
                            List.of(
                                    new Module(
                                            "Excel Skills for Business",
                                            "Spreadsheet fundamentals for data cleaning and summary.",
                                            "https://www.coursera.org/specializations/excel",
                                            "Coursera (audit)",
                                            1
                                    ),
                                    new Module(
                                            "SQL for Data Analysis",
                                            "Querying and joining relational data.",
                                            "https://www.khanacademy.org/computing/computer-programming/sql",
                                            "Khan Academy",
                                            2
                                    )
                            ),
                            true
                    )
            ));
        }
    }

    private void seedAdmin() {
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User(
                    adminEmail,
                    passwordEncoder.encode(adminPassword),
                    adminFirstName,
                    adminLastName,
                    Role.ADMIN
            );
            userRepository.save(admin);
            System.out.println("Seeded admin account: " + adminEmail
                    + " (change learnly.admin.password in application.properties before any real deployment)");
        }
    }

    private void seedTracks() {
        if (trackRepository.count() == 0) {
            trackRepository.saveAll(List.of(
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
            ));
        }
    }

    private void seedCareers() {
        if (careerRepository.count() == 0) {
            careerRepository.saveAll(List.of(
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
            ));
        }
    }

    //Posting of available and proffitable opportunities for our the users
    private void seedOpportunities() {
        if (opportunityRepository.count() == 0) {
            opportunityRepository.saveAll(List.of(
                    new Opportunity(
                            "graduate-programmes",
                            "Track opening periods and required documents early each year."
                    ),
                    new Opportunity(
                            "learnerships",
                            "Use your profile to match entry requirements by province."
                    ),
                    new Opportunity(
                            "internships",
                            "Apply with a role-specific CV and practical project evidence."
                    )
            ));
        }
    }
}
