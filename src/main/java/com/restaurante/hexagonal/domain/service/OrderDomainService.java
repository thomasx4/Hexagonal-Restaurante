package com.restaurante.hexagonal.domain.service;

import java.util.List;

import com.restaurante.hexagonal.domain.model.Order;

public class OrderDomainService {
    
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";
    
    private static final List<String> VALID_STATUSES = List.of(
        STATUS_PENDING, STATUS_IN_PROGRESS, STATUS_COMPLETED, STATUS_CANCELLED
    );
  
    public void validateStatus(String status) {
        if (!VALID_STATUSES.contains(status)) {
            throw new IllegalArgumentException(
                "Estado inválido: " + status + ". Estados válidos: " + VALID_STATUSES
            );
        }
    }

    public void validateTableAvailable(Long tableId, List<Order> activeOrders) {
        boolean hasActiveOrder = activeOrders.stream()
                .filter(o -> !STATUS_COMPLETED.equals(o.getStatus()))
                .filter(o -> !STATUS_CANCELLED.equals(o.getStatus()))
                .anyMatch(o -> tableId.equals(o.getTableId()));
        
        if (hasActiveOrder) {
            throw new IllegalStateException(
                "La mesa " + tableId + " ya tiene una orden activa"
            );
        }
    }
    
  
    public void validateStatusTransition(String currentStatus, String newStatus) {
        if (currentStatus.equals(newStatus)) {
            return; 
        }
        
      
        if (STATUS_COMPLETED.equals(currentStatus)) {
            throw new IllegalStateException("No se puede cambiar una orden ya completada");
        }
        
        if (STATUS_CANCELLED.equals(currentStatus)) {
            throw new IllegalStateException("No se puede cambiar una orden cancelada");
        }
        
        if (STATUS_PENDING.equals(currentStatus) && STATUS_IN_PROGRESS.equals(newStatus)) {
            return; 
        }
        
        if (STATUS_IN_PROGRESS.equals(currentStatus) && STATUS_COMPLETED.equals(newStatus)) {
            return;
        }
        
        if (STATUS_CANCELLED.equals(newStatus)) {
            return;
        }
        
        throw new IllegalStateException(
            "No se puede cambiar de " + currentStatus + " a " + newStatus
        );
    }
    
    
    public List<Order> findActiveOrdersByTable(Long tableId, List<Order> orders) {
        return orders.stream()
                .filter(o -> tableId.equals(o.getTableId()))
                .filter(o -> !STATUS_COMPLETED.equals(o.getStatus()))
                .filter(o -> !STATUS_CANCELLED.equals(o.getStatus()))
                .toList();
    }
}