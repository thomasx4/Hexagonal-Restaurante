package com.restaurante.hexagonal.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.restaurante.hexagonal.domain.model.Category;

public interface CategoryRepositoryPort {
    Category save (Category category);
    Optional<Category> findById (Long id);
    List<Category> findAll();
    List<Category> findByNameContaining(String searchTerm);
    boolean existsByName (String name);
    void deleteById(Long id);
}