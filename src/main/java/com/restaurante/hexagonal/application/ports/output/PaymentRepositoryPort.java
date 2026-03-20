package com.restaurante.hexagonal.application.ports.output;

import com.restaurante.hexagonal.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryPort {

    Payment save(Payment payment);

    Optional<Payment> findById(Long id);

    List<Payment> findAll();

    Payment update(Payment payment);

    void deleteById(Long id);
}