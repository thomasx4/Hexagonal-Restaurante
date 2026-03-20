package com.restaurante.hexagonal.domain.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderDetail {
    
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
