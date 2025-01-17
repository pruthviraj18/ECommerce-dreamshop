package com.application.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Ecommerce.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	//delete by using the CartID
    void deleteAllByCartId(Long id);
}
