package com.restaurante.hexagonal.application.ports.input;

import com.restaurante.hexagonal.domain.model.Table;
import java.util.List;

public interface TableInputPort {

    Table createTable(Table table);

    List<Table> getTables();

    Table getTableById(Long id);

    Table deleteTable(Long id);

    Table updateTable(Table table);
}