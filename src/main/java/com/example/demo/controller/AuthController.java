package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.AuthService;
@RestController
@RequestMapping("/auth")
public class AuthController {
   private final AuthService service;
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public UserAccount login(
            @RequestParam String email,
            @RequestParam String password) {
        return service.login(email, password);
    }
}
