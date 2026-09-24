import "./App.css";
import { useEffect, useMemo, useState } from "react";
import {
  ArrowUpRight,
  Bell,
  BookOpen,
  BriefcaseBusiness,
  Check,
  ChevronRight,
  CirclePlay,
  Flame,
  LayoutDashboard,
  Menu,
  Network,
  Search,
  Save,
  Sparkles,
  Target,
  UserRound,
  X,
} from "lucide-react";
import AuthPage from "./Pages/Auth";
import CareerPage from "./Pages/Career";
import CoursePage from "./Pages/Course1";
import CoursesPage from "./Pages/Courses";
import DashboardPage from "./Pages/Dashboard";
import MatricPage from "./Pages/Matric";
import RegisterPage from "./Pages/Register";
import { apiRequest } from "./api";

const fallbackCourses = [
  {
    id: "web",
    category: "Build",
    title: "Web foundations",
    detail: "HTML, CSS, and the internet",
    level: "Starter",
    duration: "4 weeks",
    progress: 68,
    color: "cyan",
    icon: "<>",
  },
  {
    id: "data",
    category: "Analyse",
    title: "Data storytelling",
    detail: "Turn raw numbers into decisions",
    level: "Starter",
    duration: "3 weeks",
    progress: 24,
    color: "lime",
    icon: "01",
  },
  {
    id: "career",
    category: "Launch",
    title: "Your first portfolio",
    detail: "Make proof of what you can do",
    level: "All levels",
    duration: "2 weeks",
    progress: 0,
    color: "coral",
    icon: "✦",
  },
];

const fallbackCareers = [
  {
    career: "Software developer",
    demand: "High demand",
    skills: "HTML · JavaScript · Logic",
    match: 92,
  },
  {
    career: "Data analyst",
    demand: "Growing demand",
    skills: "Spreadsheets · SQL · Insight",
    match: 78,
  },
  {
    career: "UX designer",
    demand: "Strong demand",
    skills: "Research · Systems · Empathy",
    match: 71,
  },
];

const fallbackTracks = [
  {
    title: "Matric pathway",
    subtitle: "Build confidence for your next chapter",
    points: ["Career clarity", "Digital foundations", "Opportunity map"],
  },
  {
    title: "University pathway",
    subtitle: "Turn your degree into direction",
    points: ["Portfolio projects", "Workplace fluency", "Interview practice"],
  },
];

const fallbackQuiz = {
  text: "What are you curious about right now?",
  options: [
    { id: "build", text: "Making things" },
    { id: "people", text: "Understanding people" },
    { id: "puzzles", text: "Solving puzzles" },
    { id: "voice", text: "Finding my voice" },
  ],
};

const navItems = [
  { id: "home", label: "Home", icon: LayoutDashboard },
  { id: "path", label: "My path", icon: Target },
  { id: "courses", label: "Course library", icon: BookOpen },
  { id: "career", label: "Career lab", icon: BriefcaseBusiness },
];

function App() {
  const apiBase = useMemo(
    () => import.meta.env.VITE_API_BASE || "http://localhost:8080/api",
    [],
  );
  const [activeView, setActiveView] = useState("home");
  const [mobileMenu, setMobileMenu] = useState(false);
  const [showQuiz, setShowQuiz] = useState(false);
  const [quizQuestion, setQuizQuestion] = useState(fallbackQuiz);
  const [quizResult, setQuizResult] = useState(null);
  const [quizLoading, setQuizLoading] = useState(false);
  const [showPreferences, setShowPreferences] = useState(false);
  const [toast, setToast] = useState("");
  const [search, setSearch] = useState("");
  const [tracks, setTracks] = useState(fallbackTracks);
  const [careerTiles, setCareerTiles] = useState(fallbackCareers);
  const [courses, setCourses] = useState(fallbackCourses);
  const [selectedTrack, setSelectedTrack] = useState("Matric pathway");
  const [preferences, setPreferences] = useState(() => {
    try {
      return (
        JSON.parse(localStorage.getItem("learnly.preferences")) || {
          goal: "Build job-ready skills",
          pace: "A few times a week",
          reminders: true,
        }
      );
    } catch {
      return {
        goal: "Build job-ready skills",
        pace: "A few times a week",
        reminders: true,
      };
    }
  });
  const [savedRoles, setSavedRoles] = useState(() =>
    JSON.parse(localStorage.getItem("learnly.savedRoles") || "[]"),
  );
  const [profile, setProfile] = useState(() => {
    try {
      return (
        JSON.parse(localStorage.getItem("learnly.profile")) || {
          firstName: "Tumi",
          lastName: "M.",
          email: "tumi@learnly.co.za",
          role: "Learner",
        }
      );
    } catch {
      return {
        firstName: "Tumi",
        lastName: "M.",
        email: "tumi@learnly.co.za",
        role: "Learner",
      };
    }
  });
  const [route, setRoute] = useState(() => window.location.hash || "#/home");

  useEffect(() => {
    const syncRoute = () => setRoute(window.location.hash || "#/home");
    window.addEventListener("hashchange", syncRoute);
    return () => window.removeEventListener("hashchange", syncRoute);
  }, []);

  useEffect(() => {
    const loadData = async () => {
      try {
        const [tracksRes, careersRes, coursesRes] = await Promise.all([
          fetch(`${apiBase}/tracks`),
          fetch(`${apiBase}/careers`),
          fetch(`${apiBase}/courses`),
        ]);

        if (!tracksRes.ok || !careersRes.ok || !coursesRes.ok) {
          return;
        }

        const [tracksData, careersData, coursesData] = await Promise.all([
          tracksRes.json(),
          careersRes.json(),
          coursesRes.json(),
        ]);

        setTracks(
          tracksData.map((item) => ({
            title: item.title,
            subtitle: item.audience,
            points: item.outcomes,
          })),
        );

        setCareerTiles(
          careersData.map((item, index) => ({
            career: item.name,
            demand: `${item.demand} demand`,
            skills: item.starterSkills.join(" · "),
            match: [92, 78, 71][index] || 68,
          })),
        );
        setCourses(
          coursesData.map((item) => ({
            id: item.id,
            category: "Learn",
            title: item.title,
            detail: item.description,
            level: "Self-paced",
            duration: `${item.modules?.length || 4} modules`,
            progress: 0,
            color: "cyan",
            icon: "✦",
          })),
        );
      } catch {
        // Keep fallback data when API is unavailable.
      }
    };

    loadData();
  }, [apiBase]);

  useEffect(() => {
    if (!toast) return undefined;
    const timer = window.setTimeout(() => setToast(""), 2600);
    return () => window.clearTimeout(timer);
  }, [toast]);

  const filteredCourses = courses.filter((course) =>
    `${course.title} ${course.detail} ${course.category}`
      .toLowerCase()
      .includes(search.toLowerCase()),
  );

  const navigate = (view) => {
    setActiveView(view);
    setMobileMenu(false);
  };

  const routePage = {
    "#/dashboard": <DashboardPage />,
    "#/courses": <CoursesPage />,
    "#/career": <CareerPage />,
    "#/course/web-foundations": <CoursePage />,
    "#/matric": <MatricPage />,
    "#/login": <AuthPage />,
    "#/register": <RegisterPage />,
  }[route];

  const startLearning = (course) => {
    setToast(`${course.title} added to your path`);
    setActiveView("path");
  };

  const completeAction = (message) => setToast(message);

  const openQuiz = async () => {
    // Load the richer quiz when the API is available, but keep the check-in usable offline.
    setShowQuiz(true);
    setQuizResult(null);
    setQuizLoading(true);
    try {
      const questions = await apiRequest("/quiz");
      if (questions?.[0]?.options?.length) setQuizQuestion(questions[0]);
    } catch {
      setQuizQuestion(fallbackQuiz);
    } finally {
      setQuizLoading(false);
    }
  };

  const answerQuiz = async (option) => {
    // The local mapping gives learners immediate feedback while the API is unavailable.
    setQuizLoading(true);
    try {
      const result = await apiRequest("/quiz/submit", {
        method: "POST",
        body: JSON.stringify({ selectedOptionIds: [option.id] }),
      });
      setQuizResult(result?.topMatch?.name || "Digital problem solver");
    } catch {
      setQuizResult(
        {
          build: "Software developer",
          people: "UX designer",
          puzzles: "Data analyst",
          voice: "Digital entrepreneur",
        }[option.id] || "Digital problem solver",
      );
    } finally {
      setQuizLoading(false);
    }
  };

  const savePreferences = (event) => {
    // Preferences are intentionally local-first; they are useful before account creation.
    event.preventDefault();
    localStorage.setItem("learnly.preferences", JSON.stringify(preferences));
    setShowPreferences(false);
    setToast("Preferences saved");
  };

  const toggleSavedRole = (role) => {
    const next = savedRoles.includes(role)
      ? savedRoles.filter((item) => item !== role)
      : [...savedRoles, role];
    setSavedRoles(next);
    localStorage.setItem("learnly.savedRoles", JSON.stringify(next));
    setToast(
      next.includes(role)
        ? `${role} saved to your list`
        : `${role} removed from your list`,
    );
  };

  const saveProfile = async (event) => {
    // Save locally first so profile editing still works during API or database downtime.
    event.preventDefault();
    localStorage.setItem("learnly.profile", JSON.stringify(profile));
    const token = localStorage.getItem("learnly.token");
    if (token) {
      try {
        const response = await fetch(`${apiBase}/profile`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify({
            firstName: profile.firstName,
            lastName: profile.lastName,
          }),
        });
        if (!response.ok) throw new Error("Profile sync failed");
      } catch {
        setToast("Saved on this device; API sync is unavailable");
        return;
      }
    }
    setToast("Profile saved");
  };

  const renderProfile = () => (
    <>
      <PageIntro
        eyebrow="YOUR PROFILE"
        title={
          <>
            Make this space
            <br />
            <span>feel like yours.</span>
          </>
        }
        copy="Keep your name and learning identity close. Your profile is saved locally, and syncs to the API after you sign in."
      />
      <section className="profile-layout">
        <form className="profile-panel" onSubmit={saveProfile}>
          <div className="profile-panel-heading">
            <div className="profile-large-avatar">
              {profile.firstName.slice(0, 1)}
              {profile.lastName.slice(0, 1)}
            </div>
            <div>
              <p className="eyebrow">LEARNER IDENTITY</p>
              <h2>
                {profile.firstName || "Your name"} {profile.lastName}
              </h2>
              <span>{profile.role}</span>
            </div>
          </div>
          <div className="profile-form-grid">
            <label>
              <span>First name</span>
              <input
                value={profile.firstName}
                onChange={(event) =>
                  setProfile({ ...profile, firstName: event.target.value })
                }
                required
              />
            </label>
            <label>
              <span>Last name</span>
              <input
                value={profile.lastName}
                onChange={(event) =>
                  setProfile({ ...profile, lastName: event.target.value })
                }
                required
              />
            </label>
            <label className="profile-full">
              <span>Email</span>
              <input value={profile.email} readOnly />
            </label>
          </div>
          <button type="submit">
            <Save size={16} /> Save profile
          </button>
        </form>
        <aside className="profile-side-panel">
          <p className="eyebrow">YOUR SIGNAL</p>
          <strong>5 day streak</strong>
          <span>Keep the spark alive</span>
          <div className="profile-side-line">
            <i style={{ width: "68%" }} />
          </div>
          <small>68% weekly focus</small>
          <button type="button" onClick={() => setShowPreferences(true)}>
            Edit preferences <ChevronRight size={15} />
          </button>
        </aside>
      </section>
    </>
  );

  const renderHome = () => (
    <>
      <section className="welcome-grid">
        <div className="welcome-copy">
          <p className="eyebrow">
            <Sparkles size={14} /> YOUR NEXT MOVE IS CLEARING
          </p>
          <h1>
            Keep learning.
            <br />
            <span>Keep becoming.</span>
          </h1>
          <p className="lead">
            A calm, practical space to turn curiosity into skills, skills into
            proof, and proof into opportunity.
          </p>
          <div className="hero-actions">
            <button type="button" onClick={() => navigate("path")}>
              <CirclePlay size={17} /> Continue learning
            </button>
            <button type="button" className="button-quiet" onClick={openQuiz}>
              Find my direction <ArrowUpRight size={17} />
            </button>
          </div>
          <div className="micro-proof">
            <span className="avatar-stack">
              <i>TM</i>
              <i>LN</i>
              <i>+2k</i>
            </span>
            <span>Learning with 2,184 ambitious minds</span>
          </div>
        </div>
        <div className="focus-orbit" aria-label="Your learning focus">
          <div className="orbit orbit-one" />
          <div className="orbit orbit-two" />
          <div className="focus-core">
            <span>68%</span>
            <small>weekly focus</small>
          </div>
          <span className="orbit-label label-top">
            5 day streak <Flame size={14} />
          </span>
          <span className="orbit-label label-right">Next: Portfolio</span>
          <span className="orbit-label label-bottom">12 min today</span>
        </div>
      </section>

      <section className="section-block">
        <div className="section-heading">
          <div>
            <p className="eyebrow">YOUR MOMENTUM</p>
            <h2>Small steps, visible progress.</h2>
          </div>
          <button
            className="text-button"
            type="button"
            onClick={() => navigate("path")}
          >
            Open my path <ChevronRight size={16} />
          </button>
        </div>
        <div className="progress-strip">
          <div className="progress-copy">
            <span className="progress-icon">
              <BookOpen size={20} />
            </span>
            <div>
              <strong>Web foundations</strong>
              <span>Module 4 of 6 · 18 min left</span>
            </div>
          </div>
          <div className="progress-line">
            <span style={{ width: "68%" }} />
          </div>
          <strong className="progress-value">68%</strong>
          <button
            className="square-button"
            type="button"
            onClick={() => navigate("path")}
            aria-label="Continue web foundations"
          >
            <ChevronRight size={18} />
          </button>
        </div>
      </section>

      <section className="section-block">
        <div className="section-heading">
          <div>
            <p className="eyebrow">CURATED FOR YOUR PATH</p>
            <h2>Pick your next spark.</h2>
          </div>
          <button
            className="text-button"
            type="button"
            onClick={() => navigate("courses")}
          >
            See all courses <ChevronRight size={16} />
          </button>
        </div>
        <div className="course-grid">
          {courses.map((course) => (
            <CourseCard
              key={course.id}
              course={course}
              onStart={startLearning}
            />
          ))}
        </div>
      </section>

      <section className="split-section">
        <div className="section-heading">
          <div>
            <p className="eyebrow">CAREER SIGNAL</p>
            <h2>Where could this take you?</h2>
          </div>
          <button
            className="text-button"
            type="button"
            onClick={() => navigate("career")}
          >
            Explore career lab <ChevronRight size={16} />
          </button>
        </div>
        <div className="career-list">
          {careerTiles.slice(0, 3).map((item) => (
            <CareerRow
              key={item.career}
              item={item}
              onClick={() => navigate("career")}
            />
          ))}
        </div>
      </section>
    </>
  );

  const renderPath = () => (
    <>
      <PageIntro
        eyebrow="YOUR LEARNING PATH"
        title="A route that moves with you."
        copy="Choose a direction, build the habit, and let your evidence grow one focused session at a time."
      />
      <section className="path-layout">
        <div className="path-main">
          <div className="path-header">
            <div>
              <p className="eyebrow">CURRENT FOCUS</p>
              <h2>{selectedTrack}</h2>
            </div>
            <span className="status-pill">
              <span /> On track
            </span>
          </div>
          <div className="path-steps">
            {[
              "Know your starting point",
              "Build one useful skill",
              "Make something real",
              "Share your next move",
            ].map((step, index) => (
              <div
                className={`path-step ${index < 2 ? "done" : index === 2 ? "current" : ""}`}
                key={step}
              >
                <span className="step-marker">
                  {index < 2 ? <Check size={15} /> : index + 1}
                </span>
                <div>
                  <strong>{step}</strong>
                  <span>
                    {index < 2
                      ? "Complete"
                      : index === 2
                        ? "Ready when you are"
                        : "Up next"}
                  </span>
                </div>
                {index === 2 && (
                  <button
                    className="text-button"
                    type="button"
                    onClick={() => navigate("courses")}
                  >
                    Start <ArrowUpRight size={15} />
                  </button>
                )}
              </div>
            ))}
          </div>
        </div>
        <aside className="path-side">
          <p className="eyebrow">CHOOSE YOUR LENS</p>
          {tracks.map((track) => (
            <button
              key={track.title}
              className={`track-choice ${selectedTrack === track.title ? "selected" : ""}`}
              type="button"
              onClick={() => setSelectedTrack(track.title)}
            >
              <span>
                <strong>{track.title}</strong>
                <small>{track.subtitle}</small>
              </span>
              <ChevronRight size={17} />
            </button>
          ))}
          <div className="side-note">
            <Sparkles size={18} />
            <p>
              <strong>Keep it light.</strong>
              <br />
              Your path is a guide, not a deadline.
            </p>
          </div>
        </aside>
      </section>
    </>
  );

  const renderCourses = () => (
    <>
      <PageIntro
        eyebrow="COURSE LIBRARY"
        title="Learn in your own rhythm."
        copy="Short, practical courses designed for the realities of starting from where you are."
      />
      <div className="library-tools">
        <label className="search-box">
          <Search size={17} />
          <input
            value={search}
            onChange={(event) => setSearch(event.target.value)}
            placeholder="Search skills, roles, or topics"
          />
        </label>
        <div className="filter-pills">
          <button className="filter-pill active" type="button">
            All courses
          </button>
          <button className="filter-pill" type="button">
            Starter
          </button>
          <button className="filter-pill" type="button">
            In progress
          </button>
        </div>
      </div>
      <div className="course-grid library-grid">
        {filteredCourses.map((course) => (
          <CourseCard key={course.id} course={course} onStart={startLearning} />
        ))}
      </div>
      {filteredCourses.length === 0 && (
        <div className="empty-state">
          <Search size={22} />
          <h3>No course found yet.</h3>
          <p>Try a broader search and keep exploring.</p>
        </div>
      )}
    </>
  );

  const renderCareer = () => (
    <>
      <PageIntro
        eyebrow="CAREER LAB"
        title="Your skills have somewhere to go."
        copy="See the signal in your strengths, then turn it into a next step that feels possible."
      />
      <section className="career-hero">
        <div>
          <span className="match-badge">
            <Sparkles size={14} /> PERSONALIZED SIGNAL
          </span>
          <h2>Digital problem solver</h2>
          <p>
            Your curiosity, pattern-spotting, and builder energy are pointing
            toward roles where useful things get made.
          </p>
          <button type="button" onClick={openQuiz}>
            Retake career quiz <ArrowUpRight size={17} />
          </button>
        </div>
        <div className="match-score">
          <strong>92</strong>
          <span>% match</span>
          <small>based on your interests</small>
        </div>
      </section>
      <div className="career-lab-grid">
        <section>
          <div className="section-heading">
            <div>
              <p className="eyebrow">ROLES TO EXPLORE</p>
              <h2>Follow the signal.</h2>
            </div>
          </div>
          <div className="career-list">
            {careerTiles.map((item) => (
              <CareerRow
                key={item.career}
                item={item}
                onClick={() => toggleSavedRole(item.career)}
              />
            ))}
          </div>
        </section>
        <aside className="opportunity-card">
          <p className="eyebrow">THIS WEEK'S NUDGE</p>
          <Network size={24} />
          <h3>Make your learning visible.</h3>
          <p>
            Share one tiny project with someone you trust. Feedback is a
            shortcut to clarity.
          </p>
          <button
            type="button"
            className="button-quiet"
            onClick={() => {
              localStorage.setItem(
                "learnly.reflectionPrompt",
                "Make your learning visible.",
              );
              completeAction("Reflection prompt saved for later");
            }}
          >
            Save prompt <Check size={16} />
          </button>
        </aside>
      </div>
    </>
  );

  const renderContent = () =>
    ({
      home: renderHome(),
      path: renderPath(),
      courses: renderCourses(),
      career: renderCareer(),
      profile: renderProfile(),
    })[activeView];

  if (routePage) {
    return routePage;
  }

  return (
    <div className="app-frame">
      <aside className={`sidebar ${mobileMenu ? "open" : ""}`}>
        <div className="brand-mark">
          <span>l</span>
          <strong>learnly</strong>
          <small>beta</small>
        </div>
        <div className="side-label">YOUR SPACE</div>
        <nav>
          {navItems.map(({ id, label, icon: Icon }) => (
            <button
              key={id}
              className={activeView === id ? "active" : ""}
              type="button"
              onClick={() => navigate(id)}
            >
              <Icon size={18} />
              <span>{label}</span>
              {id === "path" && <b>1</b>}
            </button>
          ))}
        </nav>
        <div className="sidebar-bottom">
          <button type="button" onClick={() => navigate("profile")}>
            <UserRound size={18} />
            <span>Profile</span>
          </button>
          <div className="side-streak">
            <Flame size={17} />
            <span>
              <strong>5 day streak</strong>
              <small>Keep the spark alive</small>
            </span>
          </div>
        </div>
      </aside>
      <main className="main-stage">
        <header className="topbar">
          <button
            className="mobile-menu"
            type="button"
            onClick={() => setMobileMenu(!mobileMenu)}
            aria-label="Open navigation"
          >
            {mobileMenu ? <X size={20} /> : <Menu size={20} />}
          </button>
          <div className="breadcrumb">
            <span>Learnly</span>
            <ChevronRight size={14} />
            <strong>
              {navItems.find((item) => item.id === activeView)?.label ||
                (activeView === "profile" ? "Profile" : "Home")}
            </strong>
          </div>
          <div className="top-actions">
            <button
              className="icon-button"
              type="button"
              onClick={() => completeAction("You are all caught up")}
              aria-label="Notifications"
            >
              <Bell size={18} />
              <i />
            </button>
            <button
              className="profile-chip"
              type="button"
              onClick={() => navigate("profile")}
            >
              <span>TM</span>
              <strong>Tumi M.</strong>
            </button>
          </div>
        </header>
        <div className="content-wrap">{renderContent()}</div>
      </main>
      {showQuiz && (
        <div
          className="modal-backdrop"
          role="presentation"
          onClick={() => setShowQuiz(false)}
        >
          <section
            className="quiz-modal"
            role="dialog"
            aria-modal="true"
            aria-labelledby="quiz-title"
            onClick={(event) => event.stopPropagation()}
          >
            <button
              className="modal-close"
              type="button"
              onClick={() => setShowQuiz(false)}
              aria-label="Close"
            >
              <X size={18} />
            </button>
            <span className="match-badge">
              <Sparkles size={14} /> 3 MINUTE CHECK-IN
            </span>
            <h2 id="quiz-title">
              {quizResult ? "Your next signal" : quizQuestion.text}
            </h2>
            <p>
              {quizResult
                ? `${quizResult} is a direction worth testing. Start with one small project and let the evidence grow.`
                : "There is no wrong answer. Pick the energy that feels closest and we will shape a useful next step."}
            </p>
            {!quizResult && (
              <div className="quiz-options">
                {quizQuestion.options.map((option) => (
                  <button
                    key={option.id}
                    type="button"
                    disabled={quizLoading}
                    onClick={() => answerQuiz(option)}
                  >
                    {option.text}
                    <ChevronRight size={17} />
                  </button>
                ))}
              </div>
            )}
            {quizLoading && (
              <p className="quiz-status">Finding your signal...</p>
            )}
            {quizResult && (
              <button
                className="quiz-result-action"
                type="button"
                onClick={() => {
                  setShowQuiz(false);
                  navigate("career");
                }}
              >
                Explore this direction <ArrowUpRight size={16} />
              </button>
            )}
          </section>
        </div>
      )}
      {showPreferences && (
        <div
          className="modal-backdrop"
          role="presentation"
          onClick={() => setShowPreferences(false)}
        >
          <section
            className="quiz-modal preferences-modal"
            role="dialog"
            aria-modal="true"
            aria-labelledby="preferences-title"
            onClick={(event) => event.stopPropagation()}
          >
            <button
              className="modal-close"
              type="button"
              onClick={() => setShowPreferences(false)}
              aria-label="Close"
            >
              <X size={18} />
            </button>
            <span className="match-badge">
              <Sparkles size={14} /> YOUR LEARNING SETTINGS
            </span>
            <h2 id="preferences-title">Shape the way you learn.</h2>
            <form className="preferences-form" onSubmit={savePreferences}>
              <label>
                <span>My main goal</span>
                <select
                  value={preferences.goal}
                  onChange={(event) =>
                    setPreferences({ ...preferences, goal: event.target.value })
                  }
                >
                  <option>Build job-ready skills</option>
                  <option>Explore career options</option>
                  <option>Prepare for university</option>
                  <option>Build a portfolio</option>
                </select>
              </label>
              <label>
                <span>My learning rhythm</span>
                <select
                  value={preferences.pace}
                  onChange={(event) =>
                    setPreferences({ ...preferences, pace: event.target.value })
                  }
                >
                  <option>A few times a week</option>
                  <option>Every day</option>
                  <option>Weekends only</option>
                  <option>Whenever I can</option>
                </select>
              </label>
              <label className="preference-toggle">
                <input
                  type="checkbox"
                  checked={preferences.reminders}
                  onChange={(event) =>
                    setPreferences({
                      ...preferences,
                      reminders: event.target.checked,
                    })
                  }
                />
                <span>Send gentle focus reminders</span>
              </label>
              <button type="submit" className="quiz-result-action">
                Save preferences <Save size={16} />
              </button>
            </form>
          </section>
        </div>
      )}
      {toast && (
        <div className="toast">
          <Check size={16} /> {toast}
        </div>
      )}
    </div>
  );
}

function PageIntro({ eyebrow, title, copy }) {
  return (
    <section className="page-intro">
      <p className="eyebrow">
        <Sparkles size={14} /> {eyebrow}
      </p>
      <h1>{title}</h1>
      <p className="lead">{copy}</p>
    </section>
  );
}

function CourseCard({ course, onStart }) {
  return (
    <article className={`course-card ${course.color}`}>
      <div className="course-top">
        <span className="course-icon">{course.icon}</span>
        <span className="course-category">{course.category}</span>
        <button
          className="card-arrow"
          type="button"
          onClick={() => onStart(course)}
          aria-label={`Start ${course.title}`}
        >
          <ArrowUpRight size={17} />
        </button>
      </div>
      <h3>{course.title}</h3>
      <p>{course.detail}</p>
      <div className="course-meta">
        <span>{course.level}</span>
        <span>{course.duration}</span>
      </div>
      <div className="mini-progress">
        <span style={{ width: `${course.progress}%` }} />
      </div>
      <div className="course-foot">
        <small>
          {course.progress ? `${course.progress}% complete` : "Ready to begin"}
        </small>
        <button
          className="inline-action"
          type="button"
          onClick={() => onStart(course)}
        >
          {course.progress ? "Continue" : "Start"} <ChevronRight size={15} />
        </button>
      </div>
    </article>
  );
}

function CareerRow({ item, onClick }) {
  return (
    <button className="career-row" type="button" onClick={onClick}>
      <span className="career-symbol">
        <BriefcaseBusiness size={18} />
      </span>
      <span className="career-info">
        <strong>{item.career}</strong>
        <small>{item.skills}</small>
      </span>
      <span className="career-match">
        <b>{item.match}%</b>
        <small>{item.demand}</small>
      </span>
      <ChevronRight size={17} />
    </button>
  );
}

export default App;
