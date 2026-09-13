package com.learnly.api.repository;

import com.learnly.api.model.Opportunity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OpportunityRepository extends MongoRepository<Opportunity, String> {}
