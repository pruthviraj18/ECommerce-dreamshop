package com.application.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByName(String name);

  boolean existsByName(String name);
}
