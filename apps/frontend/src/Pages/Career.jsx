import { ArrowUpRight, BriefcaseBusiness, Sparkles } from "lucide-react";
import { PageFrame, SignalCard } from "./PageFrame";

const roles = [
  "Software developer",
  "Data analyst",
  "UX designer",
  "Digital entrepreneur",
];

export default function Career() {
  return (
    <PageFrame
      eyebrow="CAREER LAB"
      title={
        <>
          Your skills have
          <br />
          <span>somewhere to go.</span>
        </>
      }
      copy="Turn your interests into a direction you can test, build, and make visible."
      aside={
        <SignalCard title="Make one thing visible.">
          A tiny project is often more useful than a perfect plan. Start with
          proof.
        </SignalCard>
      }
    >
      <div className="page-role-grid">
        {roles.map((role, index) => (
          <button className="page-role-card" key={role} type="button">
            <span>
              <BriefcaseBusiness size={17} />
            </span>
            <strong>{role}</strong>
            <small>
              {["92% match", "78% match", "71% match", "66% match"][index]}
            </small>
            <ArrowUpRight size={16} />
          </button>
        ))}
      </div>
      <button className="page-outline-action" type="button">
        <Sparkles size={16} /> Take the 3 minute direction check
      </button>
    </PageFrame>
  );
}
