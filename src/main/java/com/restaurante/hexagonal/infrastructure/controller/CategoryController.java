package com.restaurante.hexagonal.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.hexagonal.application.ports.input.CategoryInputPort;
import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.infrastructure.controller.dto.CategoryRequestDTO;
import com.restaurante.hexagonal.infrastructure.controller.dto.CategoryResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryInputPort categoryInputPort;

    public CategoryController(CategoryInputPort categoryInputPort) {
        this.categoryInputPort = categoryInputPort;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO request) {
        Category category = categoryInputPort.createCategory(request.getName(), request.getDescription());
        return new ResponseEntity<>(toResponseDTO(category), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<Category> categories = categoryInputPort.getAllCategories();
        List<CategoryResponseDTO> response = categories.stream()
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryInputPort.getCategoryById(id);
        return ResponseEntity.ok(toResponseDTO(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO request) {
        Category category = categoryInputPort.updateCategory(id, request.getName(), request.getDescription());
        return ResponseEntity.ok(toResponseDTO(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryInputPort.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryResponseDTO>> searchCategories(@RequestParam String term) {
        List<Category> categories = categoryInputPort.searchCategories(term);
        List<CategoryResponseDTO> response = categories.stream()
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    private CategoryResponseDTO toResponseDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

}