package com.restaurante.hexagonal.domain.service;

import com.restaurante.hexagonal.domain.model.OrderDetail;

import java.math.BigDecimal;

public class OrderDetailService {

    public BigDecimal calculateSubtotal(OrderDetail orderDetail) {
        return orderDetail.getUnitPrice()
                .multiply(BigDecimal.valueOf(orderDetail.getQuantity()));
    }

    public void validate(OrderDetail orderDetail) {

        if (orderDetail.getQuantity() == null || orderDetail.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        if (orderDetail.getUnitPrice() == null ||
                orderDetail.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
    }
}