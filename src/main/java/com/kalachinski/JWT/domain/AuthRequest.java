package com.kalachinski.JWT.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthRequest {

    @NonNull
    private String login;

    @NonNull
    private String password;
}
