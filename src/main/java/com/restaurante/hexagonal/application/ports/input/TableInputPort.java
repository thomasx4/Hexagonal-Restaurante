package com.restaurante.hexagonal.application.ports.input;

import com.restaurante.hexagonal.domain.model.Table;
import java.util.List;

public interface TableInputPort {
    Table createTable(Table table);
    Table updateTable(Long id, Table table);
    Table getTableById(Long id);
    List<Table> getTables();
    void deleteTable(Long id);
}