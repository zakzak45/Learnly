import { Search, SlidersHorizontal } from "lucide-react";
import { useMemo, useState } from "react";
import { CourseList, PageFrame } from "./PageFrame";
import { pageCourses } from "./pageData";

export default function Courses() {
  const [query, setQuery] = useState("");
  const filtered = useMemo(
    () =>
      pageCourses.filter((course) =>
        `${course.title} ${course.detail} ${course.meta}`
          .toLowerCase()
          .includes(query.toLowerCase()),
      ),
    [query],
  );

  return (
    <PageFrame
      eyebrow="COURSE LIBRARY"
      title={
        <>
          Learn in your
          <br />
          <span>own rhythm.</span>
        </>
      }
      copy="Short, practical courses designed for the realities of starting from where you are."
    >
      <div className="page-library-tools">
        <label>
          <Search size={16} />
          <input
            placeholder="Search your next skill"
            value={query}
            onChange={(event) => setQuery(event.target.value)}
          />
        </label>
        <button type="button">
          <SlidersHorizontal size={16} /> Filter
        </button>
      </div>
      <CourseList
        courses={filtered}
        onSelect={(course) => {
          window.location.hash =
            course.title === "Web foundations"
              ? "#/course/web-foundations"
              : "#/courses";
        }}
      />
      {!filtered.length && (
        <p className="page-form-error">
          No course matches “{query}”. Try another skill.
        </p>
      )}
    </PageFrame>
  );
}
