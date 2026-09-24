import { ArrowUpRight, Mail, UserRound } from "lucide-react";
import { useState } from "react";
import { PageFrame } from "./PageFrame";
import { apiRequest } from "../api";

export default function Register() {
  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const submit = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError("");
    try {
      const result = await apiRequest("/auth/register", {
        method: "POST",
        body: JSON.stringify(form),
      });
      localStorage.setItem("learnly.token", result.token);
      localStorage.setItem(
        "learnly.profile",
        JSON.stringify({
          firstName: result.firstName,
          lastName: form.lastName,
          email: result.email,
          role: result.role,
        }),
      );
      window.location.hash = "#/home";
    } catch (requestError) {
      setError(
        requestError.message ||
          "Unable to create your space. Check the API and try again.",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <PageFrame
      eyebrow="START HERE"
      title={
        <>
          Make space for
          <br />
          <span>what is next.</span>
        </>
      }
      copy="Your learning space is free, personal, and ready whenever your curiosity is."
      aside={
        <div className="page-auth-aside">
          <strong>One account. Many next moves.</strong>
          <p>
            Save courses, track your path, and come back to your goals whenever
            you need them.
          </p>
        </div>
      }
    >
      <form className="page-form" onSubmit={submit}>
        <label>
          <span>Your name</span>
          <div>
            <UserRound size={16} />
            <input
              placeholder="What should we call you?"
              value={form.firstName}
              onChange={(event) =>
                setForm({ ...form, firstName: event.target.value })
              }
              required
            />
          </div>
        </label>
        <label>
          <span>Last name</span>
          <div>
            <Mail size={16} />
            <input
              placeholder="Your surname"
              value={form.lastName}
              onChange={(event) =>
                setForm({ ...form, lastName: event.target.value })
              }
              required
            />
          </div>
        </label>
        <label>
          <span>Email address</span>
          <div>
            <Mail size={16} />
            <input
              type="email"
              placeholder="you@example.com"
              value={form.email}
              onChange={(event) =>
                setForm({ ...form, email: event.target.value })
              }
              required
            />
          </div>
        </label>
        <label>
          <span>Password</span>
          <div>
            <UserRound size={16} />
            <input
              type="password"
              minLength="8"
              placeholder="At least 8 characters"
              value={form.password}
              onChange={(event) =>
                setForm({ ...form, password: event.target.value })
              }
              required
            />
          </div>
        </label>
        {error && <p className="page-form-error">{error}</p>}
        <button type="submit" disabled={loading}>
          {loading ? "Creating..." : "Create my space"}{" "}
          <ArrowUpRight size={16} />
        </button>
        <small className="page-form-note">
          By joining, you are choosing progress over pressure.
        </small>
      </form>
    </PageFrame>
  );
}
