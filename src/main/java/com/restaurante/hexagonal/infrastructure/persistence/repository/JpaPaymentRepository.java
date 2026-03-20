package com.restaurante.hexagonal.infrastructure.persistence.repository;

import com.restaurante.hexagonal.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
}