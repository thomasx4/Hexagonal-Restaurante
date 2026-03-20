package com.restaurante.hexagonal.infrastructure.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRequestDTO {

    private Long orderId;
    private BigDecimal amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private String reference;
}