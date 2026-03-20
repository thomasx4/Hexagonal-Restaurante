package com.restaurante.hexagonal.infrastructure.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderRequestDTO {
    
    @NotNull(message = "La mesa es obligatoria")
    private Long tableId;
    
    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 200, message = "El nombre no puede exceder 200 caracteres")
    private String customerName;
    
    @Positive(message = "El total debe ser positivo")
    private BigDecimal total;
}