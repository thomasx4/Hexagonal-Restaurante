package com.restaurante.hexagonal.infrastructure.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailRequestDTO {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;

}