package com.restaurante.hexagonal.application.service;

import com.restaurante.hexagonal.application.ports.input.PaymentInputPort;
import com.restaurante.hexagonal.application.ports.output.PaymentRepositoryPort;
import com.restaurante.hexagonal.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService implements PaymentInputPort {

    private final PaymentRepositoryPort repository;

    @Override
    public Payment createPayment(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment no encontrado"));
    }

    @Override
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
        Payment existing = getPaymentById(id);

        existing.setOrderId(payment.getOrderId());
        existing.setAmount(payment.getAmount());
        existing.setPaymentMethod(payment.getPaymentMethod());
        existing.setPaymentDate(payment.getPaymentDate());
        existing.setReference(payment.getReference());

        return repository.update(existing);
    }

    @Override
    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}