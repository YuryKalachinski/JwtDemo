package com.kalachinski.JWT.controller;

import com.kalachinski.JWT.domain.AuthRequest;
import com.kalachinski.JWT.domain.User;
import com.kalachinski.JWT.security.JwtTokenProvider;
import com.kalachinski.JWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest authRequest) {
        User user = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        jwtTokenProvider.generateToken(user.getLogin());
        return "OK";
    }

    @Validated
    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid AuthRequest authRequest) {
        userService.register(User.builder()
                .login(authRequest.getLogin())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .build());
        return "OK";
    }
}
