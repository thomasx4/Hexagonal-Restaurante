package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.OrderDetail;
import com.restaurante.hexagonal.infrastructure.persistence.entity.OrderDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    public OrderDetail toDomain(OrderDetailEntity entity) {
        if (entity == null) {
            return null;
        }

        OrderDetail domain = new OrderDetail();
        domain.setId(entity.getId());
        domain.setOrderId(entity.getOrderId());
        domain.setProductId(entity.getProductId());
        domain.setQuantity(entity.getQuantity());
        domain.setUnitPrice(entity.getUnitPrice());
        domain.setSubtotal(entity.getSubtotal());

        return domain;
    }

    public OrderDetailEntity toEntity(OrderDetail domain) {
        if (domain == null) {
            return null;
        }

        OrderDetailEntity entity = new OrderDetailEntity();
        entity.setId(domain.getId());
        entity.setOrderId(domain.getOrderId());
        entity.setProductId(domain.getProductId());
        entity.setQuantity(domain.getQuantity());
        entity.setUnitPrice(domain.getUnitPrice());
        entity.setSubtotal(domain.getSubtotal());

        return entity;
    }
}