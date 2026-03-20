package com.restaurante.hexagonal.infrastructure.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderTotalPatchDTO {
    
    @Positive(message = "El total debe ser positivo")
    private BigDecimal total;
}