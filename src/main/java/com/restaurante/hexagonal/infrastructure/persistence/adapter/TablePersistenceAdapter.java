package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.restaurante.hexagonal.application.ports.output.TableRepositoryPort;
import com.restaurante.hexagonal.domain.model.Table;

import java.util.List;
import java.util.Optional;

@Component
public class TablePersistenceAdapter implements TableRepositoryPort {

    @Override
    public Table save(Table table) {
        // lógica
        return table;
    }

    @Override
    public Optional<Table> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Table> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
    }
}