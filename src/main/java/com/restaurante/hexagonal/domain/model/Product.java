package com.restaurante.hexagonal.domain.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private Boolean available;
}