package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.Order;
import com.restaurante.hexagonal.infrastructure.persistence.entity.OrderEntity;

public class OrderMapper {
    
    private OrderMapper() {}
    
    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;
        
        return OrderEntity.builder()
                .id(order.getId())
                .tableId(order.getTableId())
                .customerName(order.getCustomerName())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .total(order.getTotal())
                .build();
    }
    
    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;
        
        return Order.reconstruct(
                entity.getId(),
                entity.getTableId(),
                entity.getCustomerName(),
                entity.getOrderDate(),
                entity.getStatus(),
                entity.getTotal()
        );
    }
}