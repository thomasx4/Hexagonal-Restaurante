package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.restaurante.hexagonal.application.ports.output.OrderRepositoryPort;
import com.restaurante.hexagonal.domain.model.Order;
import com.restaurante.hexagonal.infrastructure.persistence.entity.OrderEntity;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.OrderMapper;
import com.restaurante.hexagonal.infrastructure.persistence.repository.JpaOrderRepository;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderPersistenceAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity savedEntity = jpaOrderRepository.save(entity);
        return OrderMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaOrderRepository.findById(id)
                .map(OrderMapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll().stream()
                .map(OrderMapper::toDomain)
                .toList();
    }

    @Override
    public List<Order> findByTableId(Long tableId) {
        return findAll().stream()
                .filter(order -> tableId.equals(order.getTableId()))
                .toList();
    }

    @Override
    public List<Order> findByStatus(String status) {
        return findAll().stream()
                .filter(order -> status.equals(order.getStatus()))
                .toList();
    }

    @Override
    public List<Order> findActiveOrders() {
        return findAll().stream()
                .filter(order -> !"COMPLETED".equals(order.getStatus()))
                .filter(order -> !"CANCELLED".equals(order.getStatus()))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaOrderRepository.deleteById(id);
    }
}