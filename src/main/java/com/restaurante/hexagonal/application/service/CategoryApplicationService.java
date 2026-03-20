package com.restaurante.hexagonal.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.application.ports.input.CategoryInputPort;
import com.restaurante.hexagonal.application.ports.output.CategoryRepositoryPort;
import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.domain.service.CategoryDomainService;

@Service
public class CategoryApplicationService implements CategoryInputPort {

    private final CategoryRepositoryPort port;        // Tu nombre
    private final CategoryDomainService service;      // Tu nombre

    public CategoryApplicationService(CategoryRepositoryPort port, CategoryDomainService service) {
        this.port = port;
        this.service = service;
    }

    @Override
    public Category createCategory(String name, String description) {
        service.validateUniqueName(name, port.findAll());
        Category category = Category.create(name, description);
        return port.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return port.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return port.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con id: " + id));
    }

    @Override
    public Category updCategory(Long id, String name, String description) {
        Category existing = getCategoryById(id);
        
        if (!existing.getName().equalsIgnoreCase(name)) {
            service.validateUniqueName(name, port.findAll());
        }
        
        Category updated = existing.update(name, description);
        return port.save(updated);
    }

    @Override
    public void deleteCategory(Long id) {
        getCategoryById(id); // Solo para verificar que existe
        port.deleteById(id);
    }

    @Override
    public List<Category> searchCategories(String searchTerm) {
        return port.findByNameContaining(searchTerm);
    }


}
