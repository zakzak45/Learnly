package com.learnly.api.dto;

import jakarta.validation.constraints.NotBlank;

public record BlogPostRequest(@NotBlank String title, @NotBlank String summary, @NotBlank String content, boolean published) {}
