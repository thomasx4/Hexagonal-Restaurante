package com.restaurante.hexagonal.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.hexagonal.application.ports.input.OrderInputPort;
import com.restaurante.hexagonal.domain.model.Order;
import com.restaurante.hexagonal.infrastructure.controller.dto.OrderRequestDTO;
import com.restaurante.hexagonal.infrastructure.controller.dto.OrderResponseDTO;
import com.restaurante.hexagonal.infrastructure.controller.dto.OrderStatusRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderInputPort orderInputPort;

    public OrderController(OrderInputPort orderInputPort) {
        this.orderInputPort = orderInputPort;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        Order order = orderInputPort.createOrder(
                request.getTableId(),
                request.getCustomerName(),
                request.getTotal()
        );
        return new ResponseEntity<>(toResponseDTO(order), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderInputPort.getAllOrders();
        return ResponseEntity.ok(toResponseDTOList(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        Order order = orderInputPort.getOrderById(id);
        return ResponseEntity.ok(toResponseDTO(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequestDTO request) {
        Order order = orderInputPort.updateOrder(
                id,
                request.getTableId(),
                request.getCustomerName(),
                null,
                request.getTotal()
        );
        return ResponseEntity.ok(toResponseDTO(order));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> changeOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderStatusRequestDTO request) {
        Order order = orderInputPort.changeOrderStatus(id, request.getStatus());
        return ResponseEntity.ok(toResponseDTO(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderInputPort.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByTable(@PathVariable Long tableId) {
        List<Order> orders = orderInputPort.getOrdersByTable(tableId);
        return ResponseEntity.ok(toResponseDTOList(orders));
    }

    @GetMapping("/active")
    public ResponseEntity<List<OrderResponseDTO>> getActiveOrders() {
        List<Order> orders = orderInputPort.getActiveOrders();
        return ResponseEntity.ok(toResponseDTOList(orders));
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .tableId(order.getTableId())
                .customerName(order.getCustomerName())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .total(order.getTotal())
                .build();
    }

    private List<OrderResponseDTO> toResponseDTOList(List<Order> orders) {
        return orders.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}