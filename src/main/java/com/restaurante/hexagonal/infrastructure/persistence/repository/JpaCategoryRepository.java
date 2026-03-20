package com.restaurante.hexagonal.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.hexagonal.infrastructure.persistence.entity.CategoryEntity;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByNameContainingIgnoreCase(String searchTerm);
    boolean existsByNameIgnoreCase(String name);
}