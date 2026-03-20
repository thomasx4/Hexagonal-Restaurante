package com.restaurante.hexagonal.application.ports.input;

import com.restaurante.hexagonal.domain.model.Payment;

import java.util.List;

public interface PaymentInputPort {

    Payment createPayment(Payment payment);

    Payment getPaymentById(Long id);

    List<Payment> getAllPayments();

    Payment updatePayment(Long id, Payment payment);

    void deletePayment(Long id);
}