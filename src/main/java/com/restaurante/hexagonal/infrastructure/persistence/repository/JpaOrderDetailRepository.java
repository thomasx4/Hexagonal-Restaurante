package com.restaurante.hexagonal.infrastructure.persistence.repository;

import com.restaurante.hexagonal.infrastructure.persistence.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    
}