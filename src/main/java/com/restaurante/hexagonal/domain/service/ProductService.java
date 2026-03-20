package com.restaurante.hexagonal.domain.service;

import com.restaurante.hexagonal.domain.model.Product;

public class ProductService {

    public void validateProduct(Product product) {

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new RuntimeException("Product name is required");
        }

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new RuntimeException("Product price must be greater than 0");
        }

        if (product.getCategoryId() == null) {
            throw new RuntimeException("Product must have a category");
        }
    }
}