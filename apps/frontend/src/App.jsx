import "./App.css";
import { useEffect, useMemo, useState } from "react";

const fallbackTracks = [
  {
    title: "Matric Pathway",
    subtitle: "From school leaver to confident next step",
    points: [
      "Discover careers that match your subjects, interests, and values.",
      "Find affordable online courses, TVET options, and bursary information.",
      "Build study routines and digital literacy for independent learning.",
    ],
  },
  {
    title: "University Pathway",
    subtitle: "From lecture hall to employable graduate",
    points: [
      "Map your degree to in-demand roles in South African industries.",
      "Prepare CVs, LinkedIn, and practical portfolios with real project prompts.",
      "Practice interviews and workplace communication in local contexts.",
    ],
  },
];

const fallbackCareerTiles = [
  {
    career: "Software Developer",
    demand: "High demand in fintech, retail, and telecom",
    firstStep: "Start with HTML, JavaScript, and problem solving challenges.",
  },
  {
    career: "Data Analyst",
    demand: "Growing demand in banking, logistics, and government",
    firstStep: "Learn spreadsheets, SQL basics, and storytelling with data.",
  },
  {
    career: "UX Designer",
    demand: "Strong demand in digital product teams",
    firstStep: "Practice user research, wireframing, and accessibility design.",
  },
];

const fallbackOpportunityItems = [
  "Youth employment support links and graduate programme trackers",
  "Internship and learnership preparation checklists",
  "Mentorship circles and peer accountability rooms",
];

function App() {
  const apiBase = useMemo(
    () => import.meta.env.VITE_API_BASE || "http://localhost:8080/api",
    [],
  );
  const [tracks, setTracks] = useState(fallbackTracks);
  const [careerTiles, setCareerTiles] = useState(fallbackCareerTiles);
  const [opportunityItems, setOpportunityItems] = useState(
    fallbackOpportunityItems,
  );

  useEffect(() => {
    const loadData = async () => {
      try {
        const [tracksRes, careersRes, opportunitiesRes] = await Promise.all([
          fetch(`${apiBase}/tracks`),
          fetch(`${apiBase}/careers`),
          fetch(`${apiBase}/opportunities`),
        ]);

        if (!tracksRes.ok || !careersRes.ok || !opportunitiesRes.ok) {
          return;
        }

        const [tracksData, careersData, opportunitiesData] = await Promise.all([
          tracksRes.json(),
          careersRes.json(),
          opportunitiesRes.json(),
        ]);

        setTracks(
          tracksData.map((item) => ({
            title: item.title,
            subtitle: item.audience,
            points: item.outcomes,
          })),
        );

        setCareerTiles(
          careersData.map((item) => ({
            career: item.name,
            demand: `${item.demand} demand in ${item.sectors.join(", ")}`,
            firstStep: `Build with: ${item.starterSkills.join(", ")}.`,
          })),
        );

        setOpportunityItems(
          opportunitiesData.map((item) => `${item.type}: ${item.guidance}`),
        );
      } catch {
        // Keep fallback data when API is unavailable.
      }
    };

    loadData();
  }, [apiBase]);

  return (
    <main className="page-shell">
      <section className="hero-panel">
        <div className="tagline">Localised Self-Learning Experiences</div>
        <h1>Learnly</h1>
        <p className="hero-copy">
          A digital companion for learners coming out of matric and students in
          university to build skills, choose career paths, and prepare for the
          South African job market.
        </p>
        <div className="hero-actions">
          <button type="button">Start Career Quiz</button>
          <button type="button" className="ghost">
            Explore Learning Plans
          </button>
        </div>
      </section>

      <section className="tracks-grid" aria-label="Learning tracks">
        {tracks.map((track) => (
          <article className="card" key={track.title}>
            <p className="kicker">Guided Track</p>
            <h2>{track.title}</h2>
            <p className="subtitle">{track.subtitle}</p>
            <ul>
              {track.points.map((point) => (
                <li key={point}>{point}</li>
              ))}
            </ul>
          </article>
        ))}
      </section>

      <section className="insight-band">
        <h2>Career Discovery Snapshot</h2>
        <p>
          Get practical role guidance, required skills, and a realistic first
          90-day plan for each career route.
        </p>
      </section>

      <section className="career-grid" aria-label="Career cards">
        {careerTiles.map((item) => (
          <article className="career-card" key={item.career}>
            <h3>{item.career}</h3>
            <p className="demand">{item.demand}</p>
            <p>{item.firstStep}</p>
          </article>
        ))}
      </section>

      <section className="opportunity-block">
        <h2>Opportunities That Matter</h2>
        <p>
          Designed for local realities: constrained budgets, limited access, and
          strong ambition.
        </p>
        <ul>
          {opportunityItems.map((item) => (
            <li key={item}>{item}</li>
          ))}
        </ul>
      </section>

      <section className="cta-band">
        <h2>Build your future-ready path</h2>
        <p>
          Learn independently, build employable skills, and connect to education
          and employment opportunities.
        </p>
        <button type="button" className="accent">
          Create My Plan
        </button>
      </section>
    </main>
  );
}

export default App;
