package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.restaurante.hexagonal.application.ports.output.TableRepositoryPort;
import com.restaurante.hexagonal.domain.model.Table;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.TableMapper;
import com.restaurante.hexagonal.infrastructure.persistence.repository.JpaTableRepository;

@Component
public class TablePersistenceAdapter implements TableRepositoryPort {

    private final JpaTableRepository repository;

    public TablePersistenceAdapter(JpaTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public Table save(Table table) {
        return TableMapper.toDomain(
                repository.save(TableMapper.toEntity(table))
        );
    }

    @Override
    public Optional<Table> findById(Long id) {
        return repository.findById(id)
                .map(TableMapper::toDomain);
    }

    @Override
    public List<Table> findAll() {
        return repository.findAll()
                .stream()
                .map(TableMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}