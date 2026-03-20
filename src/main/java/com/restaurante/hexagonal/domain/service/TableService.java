package com.restaurante.hexagonal.domain.service;

import com.restaurante.hexagonal.domain.model.Table;

public class TableService {

    public void validateTable(Table table) {
        
        if (table.getNumber() == null || table.getNumber() <= 0) {
            throw new RuntimeException("Table number must be greater than 0");
        }

        if (table.getCapacity() == null || table.getCapacity() <= 0) {
            throw new RuntimeException("Table capacity must be greater than 0");
        }

        if (table.getStatus() == null ) {
            throw new RuntimeException("Table status is required");
        }
    }
}
