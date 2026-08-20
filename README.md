# Learnly South Africa

Learnly is a localized self-learning and career-readiness platform concept for South African matriculants and university students. It helps learners build independent study habits, explore career pathways, and prepare for employment opportunities.

## Architecture

- `apps/frontend`: React (Vite) experience with dark bluish UI.
- `apps/backend-java`: Spring Boot + Maven API (primary backend).
- `apps/backend-node`: Express + Mongo-ready API (MERN-compatible service).

## Core Product Focus

- Career discovery for school leavers and students.
- Skills roadmaps aligned to local job demand.
- Job-market preparation for internships, learnerships, and graduate programs.

## Quick Start

### 0) Run Full Stack From Root

```bash
cd /home/zayne/Learnly
npm install
npm run dev
```

This starts:

- Frontend on `http://localhost:5173`
- Spring Boot API on `http://localhost:8080`

Alternative stack (frontend + Node API):

```bash
npm run dev:node-stack
```

### 1) Frontend

```bash
cd apps/frontend
npm install
npm run dev
```

Frontend runs on `http://localhost:5173`.

### 2) Spring Boot Backend (primary)

```bash
cd apps/backend-java
mvn spring-boot:run
```

Spring API runs on `http://localhost:8080`.

### 3) Node Backend (optional MERN service)

```bash
cd apps/backend-node
npm install
cp .env.example .env
npm run dev
```

Node API runs on `http://localhost:5001`.

## API Endpoints

Both backends expose:

- `GET /api/health`
- `GET /api/tracks`
- `GET /api/careers`
- `GET /api/opportunities`

## Frontend API Target

Frontend defaults to Spring API via:

- `VITE_API_BASE=http://localhost:8080/api`

To use Node API instead, set:

- `VITE_API_BASE=http://localhost:5001/api`

You can add this in `apps/frontend/.env`.
