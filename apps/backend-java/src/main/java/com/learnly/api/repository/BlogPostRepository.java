package com.learnly.api.repository;

import com.learnly.api.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogPostRepository extends MongoRepository<BlogPost, String> {
    List<BlogPost> findByPublishedTrue();
}
