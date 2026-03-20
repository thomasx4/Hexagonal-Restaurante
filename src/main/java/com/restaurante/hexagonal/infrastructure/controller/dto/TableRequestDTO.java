package com.restaurante.hexagonal.infrastructure.controller.dto;
import lombok.Data;

@Data
public class TableRequestDTO {
    public Long id;
    public Integer number;
    public Integer capacity;
    public Boolean status;
}
