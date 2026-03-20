package com.restaurante.hexagonal.domain.service;

import java.util.List;

import com.restaurante.hexagonal.domain.model.Order;

public class OrderDomainService {
    
    // Constantes para estados válidos
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";
    
    private static final List<String> VALID_STATUSES = List.of(
        STATUS_PENDING, STATUS_IN_PROGRESS, STATUS_COMPLETED, STATUS_CANCELLED
    );
    
    /**
     * Validar que el estado sea válido
     */
    public void validateStatus(String status) {
        if (!VALID_STATUSES.contains(status)) {
            throw new IllegalArgumentException(
                "Estado inválido: " + status + ". Estados válidos: " + VALID_STATUSES
            );
        }
    }
    
    /**
     * Validar que la mesa esté disponible para nueva orden
     */
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
    
    /**
     * Validar transición de estados
     */
    public void validateStatusTransition(String currentStatus, String newStatus) {
        if (currentStatus.equals(newStatus)) {
            return; // Mismo estado, siempre válido
        }
        
        // Reglas de negocio para cambios de estado
        if (STATUS_COMPLETED.equals(currentStatus)) {
            throw new IllegalStateException("No se puede cambiar una orden ya completada");
        }
        
        if (STATUS_CANCELLED.equals(currentStatus)) {
            throw new IllegalStateException("No se puede cambiar una orden cancelada");
        }
        
        if (STATUS_PENDING.equals(currentStatus) && STATUS_IN_PROGRESS.equals(newStatus)) {
            return; // PENDING -> IN_PROGRESS válido
        }
        
        if (STATUS_IN_PROGRESS.equals(currentStatus) && STATUS_COMPLETED.equals(newStatus)) {
            return; // IN_PROGRESS -> COMPLETED válido
        }
        
        // Cualquier estado puede cancelarse
        if (STATUS_CANCELLED.equals(newStatus)) {
            return;
        }
        
        throw new IllegalStateException(
            "No se puede cambiar de " + currentStatus + " a " + newStatus
        );
    }
    
    /**
     * Buscar órdenes activas de una mesa
     */
    public List<Order> findActiveOrdersByTable(Long tableId, List<Order> orders) {
        return orders.stream()
                .filter(o -> tableId.equals(o.getTableId()))
                .filter(o -> !STATUS_COMPLETED.equals(o.getStatus()))
                .filter(o -> !STATUS_CANCELLED.equals(o.getStatus()))
                .toList();
    }
}