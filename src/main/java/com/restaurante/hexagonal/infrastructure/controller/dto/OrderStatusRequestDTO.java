package com.restaurante.hexagonal.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderStatusRequestDTO {
    
    @NotBlank(message = "El estado es obligatorio")
    private String status;
}