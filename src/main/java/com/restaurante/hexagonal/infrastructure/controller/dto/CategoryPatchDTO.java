package com.restaurante.hexagonal.infrastructure.controller.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryPatchDTO {
    
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String name;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;
}