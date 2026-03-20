package com.restaurante.hexagonal.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.application.ports.input.OrderInputPort;
import com.restaurante.hexagonal.application.ports.output.OrderRepositoryPort;
import com.restaurante.hexagonal.application.ports.output.TableRepositoryPort;
import com.restaurante.hexagonal.domain.model.Order;
import com.restaurante.hexagonal.domain.service.OrderDomainService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderApplicationService implements OrderInputPort {

    private final OrderRepositoryPort orderRepository;
    private final TableRepositoryPort tableRepository;  
    private final OrderDomainService orderDomainService;

    public OrderApplicationService(
            OrderRepositoryPort orderRepository,
            TableRepositoryPort tableRepository,
            OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.orderDomainService = orderDomainService;
    }

    @Override
    public Order createOrder(Long tableId, String customerName, BigDecimal total) {
        tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada con id: " + tableId));
        
        List<Order> activeOrders = orderRepository.findActiveOrders();
        orderDomainService.validateTableAvailable(tableId, activeOrders);
        
        orderDomainService.validateStatus(OrderDomainService.STATUS_PENDING);
        
        Order order = Order.create(tableId, customerName, OrderDomainService.STATUS_PENDING, total);
        
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + id));
    }

    @Override
    public Order updateOrder(Long id, Long tableId, String customerName, String status, BigDecimal total) {
        Order existing = getOrderById(id);
        
        if (!existing.getStatus().equals(status)) {
            orderDomainService.validateStatusTransition(existing.getStatus(), status);
            orderDomainService.validateStatus(status);
        }
        
        if (!existing.getTableId().equals(tableId)) {
            tableRepository.findById(tableId)
                    .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada con id: " + tableId));
            
            List<Order> activeOrders = orderRepository.findActiveOrders();
            orderDomainService.validateTableAvailable(tableId, activeOrders);
        }
        
        Order updated = existing.update(tableId, customerName, status, total);
        return orderRepository.save(updated);
    }

    @Override
    public Order changeOrderStatus(Long id, String newStatus) {
        Order existing = getOrderById(id);
        
        orderDomainService.validateStatusTransition(existing.getStatus(), newStatus);
        orderDomainService.validateStatus(newStatus);
        
        Order updated = existing.changeStatus(newStatus);
        return orderRepository.save(updated);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        
        if (OrderDomainService.STATUS_COMPLETED.equals(order.getStatus())) {
            throw new IllegalStateException("No se puede eliminar una orden completada");
        }
        
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrdersByTable(Long tableId) {
        return orderRepository.findByTableId(tableId);
    }

    @Override
    public List<Order> getActiveOrders() {
        return orderRepository.findActiveOrders();
    }
}
