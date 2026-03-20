package com.restaurante.hexagonal.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.hexagonal.infrastructure.persistence.entity.OrderEntity;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
    // Vacío - solo métodos básicos de JpaRepository
}