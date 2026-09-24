import { ArrowUpRight, BookOpen, Flame, Target } from "lucide-react";
import { CourseList, PageFrame, SignalCard } from "./PageFrame";

export default function Dashboard() {
  return (
    <PageFrame
      eyebrow="YOUR LEARNING SPACE"
      title={
        <>
          Good to see you,
          <br />
          <span>Tumi.</span>
        </>
      }
      copy="You have the focus to make a little progress today. Let's keep it light and useful."
      aside={
        <SignalCard title="5 day streak">
          Your consistency is becoming a skill of its own.
        </SignalCard>
      }
    >
      <div className="page-stat-grid">
        <div>
          <Flame size={18} />
          <strong>5 days</strong>
          <small>current streak</small>
        </div>
        <div>
          <Target size={18} />
          <strong>68%</strong>
          <small>weekly focus</small>
        </div>
        <div>
          <BookOpen size={18} />
          <strong>2</strong>
          <small>courses active</small>
        </div>
      </div>
      <div className="page-dashboard-heading">
        <h2>Keep going</h2>
        <button type="button">
          Open path <ArrowUpRight size={15} />
        </button>
      </div>
      <CourseList onSelect={() => {}} />
    </PageFrame>
  );
}
