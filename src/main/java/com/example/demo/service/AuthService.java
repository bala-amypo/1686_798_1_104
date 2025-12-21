package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface AuthService {

    UserAccount register(UserAccount user);

    UserAccount login(String email, String password);
}
