package com.restaurante.hexagonal.infrastructure.controller.dto;
import lombok.Data;

@Data
public class ProductRequestDTO {
    public String name;
    public String description;
    public Double price;
    public Long categoryId;
    public boolean available;
}