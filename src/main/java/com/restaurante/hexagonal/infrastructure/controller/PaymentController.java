package com.restaurante.hexagonal.infrastructure.controller;

import com.restaurante.hexagonal.application.ports.input.PaymentInputPort;
import com.restaurante.hexagonal.domain.model.Payment;
import com.restaurante.hexagonal.infrastructure.controller.dto.PaymentRequestDTO;
import com.restaurante.hexagonal.infrastructure.controller.dto.PaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentInputPort service;

    @PostMapping
    public PaymentResponseDTO create(@RequestBody PaymentRequestDTO request) {
        Payment payment = toDomain(request);
        return toResponse(service.createPayment(payment));
    }

    @GetMapping("/{id}")
    public PaymentResponseDTO getById(@PathVariable Long id) {
        return toResponse(service.getPaymentById(id));
    }

    @GetMapping
    public List<PaymentResponseDTO> getAll() {
        return service.getAllPayments()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PaymentResponseDTO update(@PathVariable Long id,
                                     @RequestBody PaymentRequestDTO request) {
        Payment payment = toDomain(request);
        return toResponse(service.updatePayment(id, payment));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deletePayment(id);
    }

    private Payment toDomain(PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setOrderId(dto.getOrderId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setReference(dto.getReference());
        return payment;
    }

    private PaymentResponseDTO toResponse(Payment domain) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(domain.getId());
        dto.setOrderId(domain.getOrderId());
        dto.setAmount(domain.getAmount());
        dto.setPaymentMethod(domain.getPaymentMethod());
        dto.setPaymentDate(domain.getPaymentDate());
        dto.setReference(domain.getReference());
        return dto;
    }
}