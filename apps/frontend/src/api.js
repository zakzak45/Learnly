const API_BASE = import.meta.env.VITE_API_BASE || "http://localhost:8080/api";

export function getToken() {
  return localStorage.getItem("learnly.token");
}

export async function apiRequest(path, options = {}) {
  // Keep auth header handling in one place so every feature shares the same API contract.
  const headers = new Headers(options.headers || {});
  if (options.body && !headers.has("Content-Type")) {
    headers.set("Content-Type", "application/json");
  }
  const token = getToken();
  if (token) headers.set("Authorization", `Bearer ${token}`);

  const response = await fetch(`${API_BASE}${path}`, { ...options, headers });
  const body = await response.json().catch(() => null);
  if (!response.ok) {
    throw new Error(
      body?.message || body?.error || `Request failed (${response.status})`,
    );
  }
  return body;
}
