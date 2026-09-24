import {
  ArrowUpRight,
  Check,
  ChevronRight,
  Flame,
  Sparkles,
} from "lucide-react";
import { pageCourses } from "./pageData";

export function PageFrame({ eyebrow, title, copy, children, aside }) {
  return (
    <main className="page-shell page-shell-modern">
      <header className="page-shell-header">
        <div className="brand-mark">
          <span>l</span>
          <strong>learnly</strong>
          <small>beta</small>
        </div>
        <div className="page-shell-user">
          <span>TM</span>
          <strong>Tumi M.</strong>
        </div>
      </header>
      <div className="page-shell-layout">
        <section className="page-shell-content">
          <p className="eyebrow">
            <Sparkles size={14} /> {eyebrow}
          </p>
          <h1>{title}</h1>
          <p className="hero-copy">{copy}</p>
          {children}
        </section>
        {aside && <aside className="page-shell-aside">{aside}</aside>}
      </div>
    </main>
  );
}

export function CourseList({ onSelect, courses = pageCourses }) {
  return (
    <div className="page-course-list">
      {courses.map((course) => (
        <button
          className={`page-course-item ${course.tone}`}
          type="button"
          key={course.title}
          onClick={() =>
            onSelect
              ? onSelect(course)
              : (window.location.hash = "#/course/web-foundations")
          }
        >
          <span className="page-course-marker">
            {course.progress ? `${course.progress}%` : "NEW"}
          </span>
          <span>
            <strong>{course.title}</strong>
            <small>
              {course.detail} · {course.meta}
            </small>
          </span>
          <ChevronRight size={17} />
        </button>
      ))}
    </div>
  );
}

export function SignalCard({ title, children, action = "Explore" }) {
  return (
    <div className="page-signal-card">
      <div className="page-signal-top">
        <span>
          <Flame size={16} /> NEXT SIGNAL
        </span>
        <ArrowUpRight size={16} />
      </div>
      <h2>{title}</h2>
      <p>{children}</p>
      <button type="button">
        {action} <ChevronRight size={15} />
      </button>
    </div>
  );
}

export function CheckList({ items }) {
  return (
    <ul className="page-check-list">
      {items.map((item) => (
        <li key={item}>
          <span>
            <Check size={13} />
          </span>
          {item}
        </li>
      ))}
    </ul>
  );
}
