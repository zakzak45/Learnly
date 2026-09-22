package com.learnly.api.controller;

import com.learnly.api.dto.EmployabilityResponse;
import com.learnly.api.service.EmployabilityService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employability")
public class EmployabilityController {

    private final EmployabilityService employabilityService;

    public EmployabilityController(EmployabilityService employabilityService) {
        this.employabilityService = employabilityService;
    }

    @GetMapping
    public EmployabilityResponse getScore(Authentication auth) {
        return employabilityService.calculate(auth.getName());
    }
}
