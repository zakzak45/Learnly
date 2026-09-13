package com.learnly.api.service;


import com.learnly.api.dto.AuthResponse;
import com.learnly.api.dto.LoginRequest;
import com.learnly.api.dto.RegisterRequest;
import com.learnly.api.model.Role;
import com.learnly.api.model.User;
import com.learnly.api.repository.UserRepository;
import com.learnly.api.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("An account with this email already exists");
        }


        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                Role.STUDENT


        );
        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getFirstName(), user.getRole().name());

    }
    public AuthResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
        throw new IllegalArgumentException("Invalid email or password");
    }

    String token = jwtService.generateToken(user.getEmail());
    return new AuthResponse(token, user.getEmail(), user.getFirstName(), user.getRole().name());
    }
}