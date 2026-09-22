package com.learnly.api.controller;

import com.learnly.api.dto.ProfileResponse;
import com.learnly.api.dto.UpdateProfileRequest;
import com.learnly.api.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ProfileResponse getProfile(Authentication auth) {
        return profileService.getProfile(auth.getName());
    }

    @PutMapping
    public ProfileResponse updateProfile(Authentication auth, @Valid @RequestBody UpdateProfileRequest request) {
        return profileService.updateProfile(auth.getName(), request);
    }
}
