package com.restaurante.hexagonal.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurante.hexagonal.infrastructure.persistence.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    
}
