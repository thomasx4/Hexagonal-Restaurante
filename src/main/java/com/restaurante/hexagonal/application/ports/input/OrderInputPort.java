package com.restaurante.hexagonal.application.ports.input;

import java.math.BigDecimal;
import java.util.List;

import com.restaurante.hexagonal.domain.model.Order;

public interface OrderInputPort {
    Order createOrder(Long tableId, String customerName, BigDecimal total);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order updateOrder(Long id, Long tableId, String customerName, String status, BigDecimal total);
    Order changeOrderStatus(Long id, String newStatus);
    void deleteOrder(Long id);
    List<Order> getOrdersByTable(Long tableId);
    List<Order> getActiveOrders();
}
