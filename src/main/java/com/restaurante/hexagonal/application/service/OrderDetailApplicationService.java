package com.restaurante.hexagonal.application.service;


import com.restaurante.hexagonal.application.ports.input.OrderDetailInputPort;
import com.restaurante.hexagonal.application.ports.output.OrderDetailRepositoryPort;
import com.restaurante.hexagonal.domain.model.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailApplicationService implements OrderDetailInputPort {

    private final OrderDetailRepositoryPort repository;

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {


        validate(orderDetail);
        orderDetail.setSubtotal(calculateSubtotal(
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity()
        ));

        return repository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail no encontrado con id: " + id));
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return repository.findAll();
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetail orderDetail) {

        OrderDetail existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail no encontrado con id: " + id));

        validate(orderDetail);

        existing.setOrderId(orderDetail.getOrderId());
        existing.setProductId(orderDetail.getProductId());
        existing.setQuantity(orderDetail.getQuantity());
        existing.setUnitPrice(orderDetail.getUnitPrice());

        existing.setSubtotal(calculateSubtotal(
                existing.getUnitPrice(),
                existing.getQuantity()
        ));

        return repository.update(existing);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        repository.deleteById(id);
    }


    private BigDecimal calculateSubtotal(BigDecimal unitPrice, Integer quantity) {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    private void validate(OrderDetail orderDetail) {
        if (orderDetail.getQuantity() == null || orderDetail.getQuantity() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        if (orderDetail.getUnitPrice() == null || orderDetail.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }
    }
}