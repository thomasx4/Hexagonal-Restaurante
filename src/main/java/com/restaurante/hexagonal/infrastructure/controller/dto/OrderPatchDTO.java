package com.restaurante.hexagonal.infrastructure.controller.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderPatchDTO {
    
    private Long tableId;
    
    @Size(max = 200, message = "El nombre no puede exceder 200 caracteres")
    private String customerName;
    
    private String status;
}