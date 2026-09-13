package com.learnly.api.repository;

import com.learnly.api.model.CareerPath;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CareerPathRepository extends MongoRepository<CareerPath, String> {}
