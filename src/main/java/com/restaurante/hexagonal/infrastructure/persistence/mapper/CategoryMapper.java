package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.infrastructure.persistence.entity.CategoryEntity;

public class CategoryMapper {
      private CategoryMapper() {
        // Constructor privado para clase utilitaria
    }
    
    public static CategoryEntity toEntity(Category category) {
        if (category == null) return null;
        
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
    
    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;
        
        return Category.reconstruct(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}
