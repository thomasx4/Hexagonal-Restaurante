package com.restaurante.hexagonal.application.ports.input;

import java.util.List;

import com.restaurante.hexagonal.domain.model.Order;

public interface OrderInputPort {
    Order createOrder(Long tableId, String customerName, Double total);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order updateOrder(Long id, Long tableId, String customerName, String status, Double total);
    Order changeOrderStatus(Long id, String newStatus);
    void deleteOrder(Long id);
    List<Order> getOrdersByTable(Long tableId);
    List<Order> getActiveOrders();
}
