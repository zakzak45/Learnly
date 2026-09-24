import { LockKeyhole, Mail, Sparkles } from "lucide-react";
import { useState } from "react";
import { PageFrame } from "./PageFrame";
import { apiRequest } from "../api";

export default function Auth() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const submit = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError("");
    try {
      const result = await apiRequest("/auth/login", {
        method: "POST",
        body: JSON.stringify(form),
      });
      localStorage.setItem("learnly.token", result.token);
      localStorage.setItem(
        "learnly.profile",
        JSON.stringify({
          firstName: result.firstName,
          lastName: "",
          email: result.email,
          role: result.role,
        }),
      );
      window.location.hash = "#/home";
    } catch (requestError) {
      setError(
        requestError.message ||
          "Unable to sign in. Check the API and try again.",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <PageFrame
      eyebrow="WELCOME BACK"
      title={
        <>
          Your next move
          <br />
          <span>starts here.</span>
        </>
      }
      copy="Sign in to pick up your learning rhythm, save your progress, and keep your direction close."
      aside={
        <div className="page-auth-aside">
          <Sparkles size={22} />
          <strong>Learning should feel like momentum.</strong>
          <p>Keep your notes, courses, and career signals in one calm space.</p>
        </div>
      }
    >
      <form className="page-form" onSubmit={submit}>
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
            <LockKeyhole size={16} />
            <input
              type="password"
              placeholder="Your password"
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
          {loading ? "Signing in..." : "Sign in"} <Sparkles size={16} />
        </button>
        <small className="page-form-note">
          New to Learnly? <a href="#/register">Create your free space</a>
        </small>
      </form>
    </PageFrame>
  );
}
