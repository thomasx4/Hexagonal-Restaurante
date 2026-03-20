package com.restaurante.hexagonal.infrastructure.controller;

import com.restaurante.hexagonal.application.ports.input.OrderDetailInputPort;
import com.restaurante.hexagonal.domain.model.OrderDetail;
import com.restaurante.hexagonal.infrastructure.controller.dto.OrderDetailRequestDTO;
import com.restaurante.hexagonal.infrastructure.controller.dto.OrderDetailResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailInputPort service;

    @PostMapping
    public OrderDetailResponseDTO create(@RequestBody OrderDetailRequestDTO request) {
        OrderDetail orderDetail = toDomain(request);
        return toResponse(service.createOrderDetail(orderDetail));
    }

    @GetMapping("/{id}")
    public OrderDetailResponseDTO getById(@PathVariable Long id) {
        return toResponse(service.getOrderDetailById(id));
    }

    @GetMapping
    public List<OrderDetailResponseDTO> getAll() {
        return service.getAllOrderDetails()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public OrderDetailResponseDTO update(@PathVariable Long id,
                                         @RequestBody OrderDetailRequestDTO request) {
        OrderDetail orderDetail = toDomain(request);
        return toResponse(service.updateOrderDetail(id, orderDetail));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteOrderDetail(id);
    }


    private OrderDetail toDomain(OrderDetailRequestDTO dto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(dto.getOrderId());
        orderDetail.setProductId(dto.getProductId());
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setUnitPrice(dto.getUnitPrice());
        return orderDetail;
    }

    private OrderDetailResponseDTO toResponse(OrderDetail domain) {
        OrderDetailResponseDTO dto = new OrderDetailResponseDTO();
        dto.setId(domain.getId());
        dto.setOrderId(domain.getOrderId());
        dto.setProductId(domain.getProductId());
        dto.setQuantity(domain.getQuantity());
        dto.setUnitPrice(domain.getUnitPrice());
        dto.setSubtotal(domain.getSubtotal());
        return dto;
    }
}