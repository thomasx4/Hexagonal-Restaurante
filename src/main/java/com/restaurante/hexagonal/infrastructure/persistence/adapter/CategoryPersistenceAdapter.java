package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.restaurante.hexagonal.application.ports.output.CategoryRepositoryPort;
import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.infrastructure.persistence.entity.CategoryEntity;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.CategoryMapper;
import com.restaurante.hexagonal.infrastructure.persistence.repository.JpaCategoryRepository;

@Component
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {

    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryPersistenceAdapter(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = CategoryMapper.toEntity(category);
        CategoryEntity savedEntity = jpaCategoryRepository.save(entity);
        return CategoryMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id)
                .map(CategoryMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll().stream()
                .map(CategoryMapper::toDomain)
                .toList();
    }

    @Override
    public List<Category> findByNameContaining(String searchTerm) {
        return jpaCategoryRepository.findByNameContainingIgnoreCase(searchTerm).stream()
                .map(CategoryMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return jpaCategoryRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public void deleteById(Long id) {
        jpaCategoryRepository.deleteById(id);
    }
}