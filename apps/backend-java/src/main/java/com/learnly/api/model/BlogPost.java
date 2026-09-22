package com.learnly.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "blog_posts")
public class BlogPost {

    @Id
    private String id;
    private String title;
    private String summary;
    private String content;
    private String authorEmail;
    private Instant publishedAt;
    private boolean published;

    public BlogPost() {}

    public BlogPost(String title, String summary, String content, String authorEmail, boolean published) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.authorEmail = authorEmail;
        this.published = published;
        this.publishedAt = published ? Instant.now() : null;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthorEmail() { return authorEmail; }
    public void setAuthorEmail(String authorEmail) { this.authorEmail = authorEmail; }
    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }
    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }
}
