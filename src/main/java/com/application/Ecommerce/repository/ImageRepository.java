package com.application.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Ecommerce.model.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
