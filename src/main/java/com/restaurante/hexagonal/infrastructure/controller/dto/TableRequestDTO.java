package com.restaurante.hexagonal.infrastructure.controller.dto;
import lombok.Data;

@Data
public class TableRequestDTO {
    private Long number;
    private Integer capacity;
    private boolean status;
}
