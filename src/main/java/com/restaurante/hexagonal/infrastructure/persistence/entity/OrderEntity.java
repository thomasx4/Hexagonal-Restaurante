package com.restaurante.hexagonal.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "table_id", nullable = false)
    private Long tableId;
    
    @Column(name = "customer_name", nullable = false, length = 200)
    private String customerName;
    
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @Column(name = "status", length = 50)
    private String status;
    
@Column(name = "total", precision = 10, scale = 2)
private BigDecimal total;
    
    @ManyToOne
    @JoinColumn(name = "table_id", insertable = false, updatable = false)
    private TableEntity table;
}