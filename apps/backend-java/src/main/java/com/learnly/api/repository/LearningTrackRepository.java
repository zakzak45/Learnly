package com.learnly.api.repository;

import com.learnly.api.model.LearningTrack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearningTrackRepository extends MongoRepository<LearningTrack, String> {}