import { CirclePlay, Clock3, Layers3, Sparkles } from "lucide-react";
import { CheckList, PageFrame } from "./PageFrame";

export default function Course1() {
  return (
    <PageFrame
      eyebrow="COURSE / WEB FOUNDATIONS"
      title={
        <>
          Build your first
          <br />
          <span>digital doorway.</span>
        </>
      }
      copy="A practical introduction to how the web works, made for curious beginners."
      aside={
        <div className="page-course-stats">
          <span>
            <Clock3 size={17} /> 4 weeks
          </span>
          <span>
            <Layers3 size={17} /> 6 modules
          </span>
          <span>
            <Sparkles size={17} /> Beginner friendly
          </span>
        </div>
      }
    >
      <div className="page-course-hero">
        <div className="page-course-play">
          <CirclePlay size={31} />
        </div>
        <div>
          <strong>Module 4 · How websites think</strong>
          <p>18 minutes · 68% complete</p>
        </div>
        <button type="button">Continue</button>
      </div>
      <h2 className="page-subheading">What you will make</h2>
      <CheckList
        items={[
          "A responsive personal landing page",
          "A simple portfolio project to share",
          "The confidence to read and change code",
        ]}
      />
    </PageFrame>
  );
}
