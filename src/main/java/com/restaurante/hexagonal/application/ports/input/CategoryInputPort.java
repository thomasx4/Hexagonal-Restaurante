package com.restaurante.hexagonal.application.ports.input;

import java.util.List;

import com.restaurante.hexagonal.domain.model.Category;

public interface CategoryInputPort {
 Category createCategory(String name, String description);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category updCategory(Long id, String name, String description);
    void deleteCategory(Long id);
    List<Category> searchCategories(String searchTerm);  
}