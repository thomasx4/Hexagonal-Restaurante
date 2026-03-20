package com.restaurante.hexagonal.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.application.ports.input.TableInputPort;
import com.restaurante.hexagonal.application.ports.output.TableRepositoryPort;
import com.restaurante.hexagonal.domain.model.Table;

@Service
public class TableApplicationService implements TableInputPort {

    private final TableRepositoryPort repository;

    public TableApplicationService(TableRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Table createTable(Table table) {
        return repository.save(table);
    }

    @Override
    public Table updateTable(Long id, Table table) {

        Table existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with id: " + id));

        Table updated = existing.update(
                table.getNumber(),
                table.getCapacity(),
                table.getStatus()
        );

        return repository.save(updated);
    }

    @Override
    public Table getTableById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with id: " + id));
    }

    @Override
    public List<Table> getTables() {
        return repository.findAll();
    }

    @Override
    public void deleteTable(Long id) {
        repository.deleteById(id);
    }
}