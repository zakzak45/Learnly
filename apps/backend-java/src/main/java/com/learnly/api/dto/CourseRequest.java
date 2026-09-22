package com.learnly.api.dto;

import com.learnly.api.model.Module;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CourseRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String category,
        @NotEmpty List<Module> modules,
        boolean published
) {}
