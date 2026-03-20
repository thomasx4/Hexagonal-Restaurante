package com.restaurante.hexagonal.application.ports.input;

import com.restaurante.hexagonal.domain.model.OrderDetail;

import java.util.List;

public interface OrderDetailInputPort {
 
    OrderDetail createOrderDetail(OrderDetail orderDetail);

    OrderDetail getOrderDetailById(Long id);

    List<OrderDetail> getAllOrderDetails();

    OrderDetail updateOrderDetail(Long id, OrderDetail orderDetail);

    void deleteOrderDetail(Long id);
}