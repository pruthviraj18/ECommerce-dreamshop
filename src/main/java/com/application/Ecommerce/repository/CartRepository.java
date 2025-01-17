package com.application.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Ecommerce.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
