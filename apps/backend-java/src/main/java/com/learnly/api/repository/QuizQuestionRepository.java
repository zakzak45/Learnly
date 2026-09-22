package com.learnly.api.repository;

import com.learnly.api.model.QuizQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizQuestionRepository extends MongoRepository<QuizQuestion, String> {
}
