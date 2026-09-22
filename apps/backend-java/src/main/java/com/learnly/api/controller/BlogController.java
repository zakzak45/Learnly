package com.learnly.api.controller;

import com.learnly.api.dto.BlogPostRequest;
import com.learnly.api.model.BlogPost;
import com.learnly.api.repository.BlogPostRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogPostRepository blogPostRepository;

    public BlogController(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @GetMapping
    public List<BlogPost> list() {
        return blogPostRepository.findByPublishedTrue();
    }

    @GetMapping("/{id}")
    public BlogPost get(@PathVariable String id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BlogPost create(@Valid @RequestBody BlogPostRequest request, Authentication auth) {
        return blogPostRepository.save(new BlogPost(
                request.title(), request.summary(), request.content(), auth.getName(), request.published()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BlogPost update(@PathVariable String id, @Valid @RequestBody BlogPostRequest request) {
        BlogPost post = get(id);
        post.setTitle(request.title());
        post.setSummary(request.summary());
        post.setContent(request.content());
        post.setPublished(request.published());
        return blogPostRepository.save(post);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id) {
        blogPostRepository.deleteById(id);
    }
}
