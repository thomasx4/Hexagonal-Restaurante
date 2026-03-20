package com.restaurante.hexagonal.domain.service;

import com.restaurante.hexagonal.domain.model.Payment;

import java.math.BigDecimal;

public class PaymentService {


    public void validate(Payment payment) {

        if (payment.getAmount() == null ||
                payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }

        if (payment.getPaymentMethod() == null || payment.getPaymentMethod().isEmpty()) {
            throw new IllegalArgumentException("El método de pago es obligatorio");
        }
    }
}