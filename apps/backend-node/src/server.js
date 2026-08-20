import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import mongoose from "mongoose";
import { careers, opportunities, tracks } from "./data.js";

dotenv.config();

const app = express();
const port = process.env.PORT || 5001;

app.use(cors());
app.use(express.json());

const connectMongo = async () => {
  const mongoUri = process.env.MONGO_URI;
  if (!mongoUri) {
    console.warn(
      "MONGO_URI not provided. Running API without MongoDB connection.",
    );
    return;
  }

  try {
    await mongoose.connect(mongoUri);
    console.info("Connected to MongoDB");
  } catch (error) {
    console.warn("MongoDB connection failed. Continuing with in-memory data.");
    console.warn(error.message);
  }
};

app.get("/api/health", (_req, res) => {
  res.json({ status: "ok", service: "learnly-node-api" });
});

app.get("/api/tracks", (_req, res) => {
  res.json(tracks);
});

app.get("/api/careers", (_req, res) => {
  res.json(careers);
});

app.get("/api/opportunities", (_req, res) => {
  res.json(opportunities);
});

app.listen(port, async () => {
  await connectMongo();
  console.info(`Node API listening on http://localhost:${port}`);
});
