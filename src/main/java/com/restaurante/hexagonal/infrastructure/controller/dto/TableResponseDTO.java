package com.restaurante.hexagonal.infrastructure.controller.dto;
import lombok.Data;
    
@Data
public class TableResponseDTO {
    private Long id;
    private Long number;
    private Integer capacity;
    private boolean status;
}
