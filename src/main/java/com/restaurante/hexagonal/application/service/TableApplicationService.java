package com.restaurante.hexagonal.application.service;

import com.restaurante.hexagonal.domain.model.Table;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.application.ports.input.TableInputPort;
import com.restaurante.hexagonal.application.ports.output.TableRepositoryPort;

@Service
public class TableApplicationService implements TableInputPort {

    private final TableRepositoryPort tableRepositoryPort;

    public TableApplicationService(TableRepositoryPort tableRepositoryPort) {
        this.tableRepositoryPort = tableRepositoryPort;
    }

    @Override
    public Table createTable(Table table) {
        return tableRepositoryPort.save(table);
    }

    @Override
    public List<Table> getTables() {
        return tableRepositoryPort.findAll();
    }

    @Override
    public Table getTableById(Long id) {
        return tableRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with id: " + id));
    }

    @Override
    public Table deleteTable(Long id) {
        Table table = getTableById(id);
        tableRepositoryPort.deleteById(id);
        return table;
    }

    @Override
    public Table updateTable(Table table) {
        Table existing = getTableById(table.getId());

        // Si tienes método update en Table (recomendado)
        Table updated = existing.update(
                table.getNumber(),
                table.getCapacity(),
                table.getStatus()
        );

        return tableRepositoryPort.save(updated);
    }
}