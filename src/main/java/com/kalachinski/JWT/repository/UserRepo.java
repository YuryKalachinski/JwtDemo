package com.kalachinski.JWT.repository;

import com.kalachinski.JWT.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
