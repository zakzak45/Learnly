package com.learnly.api.service;

import com.learnly.api.dto.ProfileResponse;
import com.learnly.api.dto.UpdateProfileRequest;
import com.learnly.api.model.User;
import com.learnly.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileResponse getProfile(String email) {
        User user = findUser(email);
        return toResponse(user);
    }

    public ProfileResponse updateProfile(String email, UpdateProfileRequest request) {
        User user = findUser(email);
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        userRepository.save(user);
        return toResponse(user);
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private ProfileResponse toResponse(User user) {
        return new ProfileResponse(user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole().name());
    }
}
