package com.learnly.api.model;

import java.util.List;

public record LearningTrack(String id, String title, String audience, List<String> outcomes) {
}
