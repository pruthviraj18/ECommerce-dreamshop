package com.application.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
