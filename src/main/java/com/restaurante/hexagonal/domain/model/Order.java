package com.restaurante.hexagonal.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    
    private final Long id;
    private final Long tableId;           
    private final String customerName;
    private final LocalDateTime orderDate;
    private final String status;
    private final Double total;
    
    private Order(Builder builder) {
        this.id = builder.id;
        this.tableId = builder.tableId;
        this.customerName = builder.customerName;
        this.orderDate = builder.orderDate;
        this.status = builder.status;
        this.total = builder.total;
        validate();
    }
    

    public Long getId() { return id; }
    public Long getTableId() { return tableId; }
    public String getCustomerName() { return customerName; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public Double getTotal() { return total; }
    

    private void validate() {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        if (customerName.length() > 200) {
            throw new IllegalArgumentException("El nombre no puede exceder 200 caracteres");
        }
        if (tableId == null) {
            throw new IllegalArgumentException("La orden debe estar asociada a una mesa");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
        if (total != null && total < 0) {
            throw new IllegalArgumentException("El total no puede ser negativo");
        }
    }
    
    public Order update(Long tableId, String customerName, String status, Double total) {
        return new Builder()
                .id(this.id)
                .tableId(tableId)
                .customerName(customerName)
                .orderDate(this.orderDate)
                .status(status)
                .total(total)
                .build();
    }
    
    public Order changeStatus(String newStatus) {
        return new Builder()
                .id(this.id)
                .tableId(this.tableId)
                .customerName(this.customerName)
                .orderDate(this.orderDate)
                .status(newStatus)
                .total(this.total)
                .build();
    }
    
    public Order updateTotal(Double newTotal) {
        return new Builder()
                .id(this.id)
                .tableId(this.tableId)
                .customerName(this.customerName)
                .orderDate(this.orderDate)
                .status(this.status)
                .total(newTotal)
                .build();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", status='" + status + '\'' +
                ", total=" + total +
                '}';
    }
    
    public static class Builder {
        private Long id;
        private Long tableId;
        private String customerName;
        private LocalDateTime orderDate;
        private String status;
        private Double total;
        
        public Builder id(Long id) { this.id = id; return this; }
        public Builder tableId(Long tableId) { this.tableId = tableId; return this; }
        public Builder customerName(String customerName) { this.customerName = customerName; return this; }
        public Builder orderDate(LocalDateTime orderDate) { this.orderDate = orderDate; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder total(Double total) { this.total = total; return this; }
        
        public Order build() {
            return new Order(this);
        }
    }
    
    public static Order create(Long tableId, String customerName, String status, Double total) {
        return new Builder()
                .tableId(tableId)
                .customerName(customerName)
                .orderDate(LocalDateTime.now())
                .status(status)
                .total(total != null ? total : 0.0)
                .build();
    }
    
    public static Order reconstruct(Long id, Long tableId, String customerName, 
                                    LocalDateTime orderDate, String status, Double total) {
        return new Builder()
                .id(id)
                .tableId(tableId)
                .customerName(customerName)
                .orderDate(orderDate)
                .status(status)
                .total(total)
                .build();
    }
}