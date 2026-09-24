import { ArrowUpRight, GraduationCap, Map } from "lucide-react";
import { CheckList, PageFrame } from "./PageFrame";

export default function Matric() {
  return (
    <PageFrame
      eyebrow="MATRIC PATHWAY"
      title={
        <>
          Your next chapter
          <br />
          <span>can be mapped.</span>
        </>
      }
      copy="Explore options after school without needing all the answers today."
      aside={
        <div className="page-path-aside">
          <Map size={22} />
          <strong>Start with your signal.</strong>
          <p>
            Subject choices, interests, and values can point toward more than
            one good future.
          </p>
        </div>
      }
    >
      <div className="page-path-steps">
        <div>
          <span>
            <GraduationCap size={16} />
          </span>
          <strong>Understand your options</strong>
          <small>University, TVET, learnerships, and work-ready routes.</small>
        </div>
        <div>
          <span>02</span>
          <strong>Find a direction to test</strong>
          <small>Use small projects to discover what gives you energy.</small>
        </div>
        <div>
          <span>03</span>
          <strong>Build your evidence</strong>
          <small>Turn your effort into a portfolio you can show.</small>
        </div>
      </div>
      <CheckList
        items={[
          "No expensive equipment required",
          "Local context and opportunity guidance",
          "A plan you can change as you learn",
        ]}
      />
      <button className="page-outline-action" type="button">
        Explore the pathway <ArrowUpRight size={16} />
      </button>
    </PageFrame>
  );
}
