package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import com.restaurante.hexagonal.application.ports.output.PaymentRepositoryPort;
import com.restaurante.hexagonal.domain.model.Payment;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.PaymentMapper;
import com.restaurante.hexagonal.infrastructure.persistence.repository.JpaPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentRepositoryPort {

    private final JpaPaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    public Payment save(Payment payment) {
        return mapper.toDomain(repository.save(mapper.toEntity(payment)));
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Payment update(Payment payment) {
        return mapper.toDomain(repository.save(mapper.toEntity(payment)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}