package com.example.demo.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repository;

    public AuthServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccount register(UserAccount user) {

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        user.setPasswordHash(hash(user.getPasswordHash()));
        user.setActive(true);

        return repository.save(user);
    }

    @Override
    public UserAccount login(String email, String password) {

        UserAccount user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getPasswordHash().equals(hash(password))) {
            throw new BadRequestException("Invalid credentials");
        }

        return user;
    }
    
    private String hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
