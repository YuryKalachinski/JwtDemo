package com.kalachinski.JWT.controller;

import com.kalachinski.JWT.domain.AuthRequest;
import com.kalachinski.JWT.domain.User;
import com.kalachinski.JWT.security.JwtTokenProvider;
import com.kalachinski.JWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
public class AuthController {

    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/auth")
    public String auth(@RequestBody @Valid AuthRequest authRequest) {
        User user = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        return jwtTokenProvider.generateToken(user.getLogin());
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid AuthRequest authRequest) {
        userService.register(User.builder()
                .login(authRequest.getLogin())
                .password(authRequest.getPassword())
                .build());
        return "User " + authRequest.getLogin() + " is registered.";
    }

    @GetMapping("/principal")
    public Authentication getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }
}
