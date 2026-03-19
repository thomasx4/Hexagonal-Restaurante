package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.application.ports.output.ProductRepositoryPort;
import com.restaurante.hexagonal.domain.model.Product;
import com.restaurante.hexagonal.infrastructure.persistence.entity.ProductEntity;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.ProductMapper;
import com.restaurante.hexagonal.infrastructure.persistence.repository.JpaProductRepository;

@Service
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final JpaProductRepository repository;

    public ProductPersistenceAdapter(JpaProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = repository.save(entity);
        return ProductMapper.toModel(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id)
                .map(ProductMapper::toModel);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}