package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.Payment;
import com.restaurante.hexagonal.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toDomain(PaymentEntity entity) {
        if (entity == null) return null;

        Payment payment = new Payment();
        payment.setId(entity.getId());
        payment.setOrderId(entity.getOrderId());
        payment.setAmount(entity.getAmount());
        payment.setPaymentMethod(entity.getPaymentMethod());
        payment.setPaymentDate(entity.getPaymentDate());
        payment.setReference(entity.getReference());

        return payment;
    }

    public PaymentEntity toEntity(Payment domain) {
        if (domain == null) return null;

        PaymentEntity entity = new PaymentEntity();
        entity.setId(domain.getId());
        entity.setOrderId(domain.getOrderId());
        entity.setAmount(domain.getAmount());
        entity.setPaymentMethod(domain.getPaymentMethod());
        entity.setPaymentDate(domain.getPaymentDate());
        entity.setReference(domain.getReference());

        return entity;
    }
}