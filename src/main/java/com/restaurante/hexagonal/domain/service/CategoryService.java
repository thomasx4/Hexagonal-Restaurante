package com.restaurante.hexagonal.domain.service;

import java.util.List;

import com.restaurante.hexagonal.domain.model.Category;

public class CategoryService {

    public void validateUniqueName(String name, List<Category> existinCategories){
        boolean exists = existinCategories.stream().anyMatch(c -> c.getName().equalsIgnoreCase(name));

        if (exists) {
            throw new IllegalArgumentException("Ya existe una categoria con el nombre: "+name);
        }
    }


    public void validateCanDelete(Long CategoryId, int productCount){
        if (productCount > 0) {
            throw new IllegalArgumentException("No se puede eliminar la categoria porque tiene "+ productCount+ 
                " productos asociados");
            
        }
    }

    public List<Category>searchByNAme(String searchTerm, List<Category> categories){
        String term = searchTerm.toLowerCase().trim();
        return categories.stream()
        .filter(c -> c.getName().toLowerCase().contains(term))
        .toList();
    }

}
