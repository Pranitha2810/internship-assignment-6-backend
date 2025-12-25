package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://intership-assignment-6-frontend.onrender.com"
})
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    // 🔹 REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        userRepo.save(user);
        return "User registered successfully";
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        User dbUser = userRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(dbUser.getUsername());
        return Map.of("token", token);
    }
}
