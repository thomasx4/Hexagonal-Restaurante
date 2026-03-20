package com.restaurante.hexagonal.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurante.hexagonal.infrastructure.persistence.entity.TableEntity;

public interface JpaTableRepository extends JpaRepository<TableEntity, Long> {
}