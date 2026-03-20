package com.restaurante.hexagonal.application.ports.output;

import java.util.List;
import java.util.Optional;
import com.restaurante.hexagonal.domain.model.Table;

public interface TableRepositoryPort {
    Table save(Table table);
    Optional<Table> findById(Long id);
    List<Table> findAll();
    void deleteById(Long id);
}