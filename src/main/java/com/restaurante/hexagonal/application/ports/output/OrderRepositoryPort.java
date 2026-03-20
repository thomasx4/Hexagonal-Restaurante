package com.restaurante.hexagonal.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.restaurante.hexagonal.domain.model.Order;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findByTableId(Long tableId);
    List<Order> findByStatus(String status);
    List<Order> findActiveOrders();
    void deleteById(Long id);
}