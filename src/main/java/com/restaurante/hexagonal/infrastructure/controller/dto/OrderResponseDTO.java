package com.restaurante.hexagonal.infrastructure.controller.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDTO {
    private Long id;
    private Long tableId;
    private String customerName;
    private LocalDateTime orderDate;
    private String status;
    private Double total;
}