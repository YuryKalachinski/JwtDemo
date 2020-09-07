package com.kalachinski.JWT.service;

import com.kalachinski.JWT.domain.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAllUsers();

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    User findById(Long id);

    void delete(Long id);
}
