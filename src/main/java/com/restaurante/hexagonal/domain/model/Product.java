package com.restaurante.hexagonal.domain.model;

import java.util.Objects;

public class Product {

    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final Long categoryId;
    private final Boolean available;

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.categoryId = builder.categoryId;
        this.available = builder.available;
        this.validate();
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public Long getCategoryId() { return categoryId; }
    public Boolean getAvailable() { return available; }

    // Validaciones de negocio
    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }

        if (categoryId == null) {
            throw new IllegalArgumentException("Product must have a category");
        }
    }

    // Método de negocio (update)
    public Product update(String name, String description, Double price, Long categoryId, Boolean available) {
        return new Builder()
                .id(this.id)
                .name(name)
                .description(description)
                .price(price)
                .categoryId(categoryId)
                .available(available)
                .build();
    }

    // Método de negocio (activar/desactivar)
    public Product changeAvailability(Boolean status) {
        return new Builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .categoryId(this.categoryId)
                .available(status)
                .build();
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Builder
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Double price;
        private Long categoryId;
        private Boolean available;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder available(Boolean available) {
            this.available = available;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    // Factory method (crear nuevo producto sin ID)
    public static Product create(String name, String description, Double price, Long categoryId, Boolean available) {
        return new Builder()
                .name(name)
                .description(description)
                .price(price)
                .categoryId(categoryId)
                .available(available)
                .build();
    }
}