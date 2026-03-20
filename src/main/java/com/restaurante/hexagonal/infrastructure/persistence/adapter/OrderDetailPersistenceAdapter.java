package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import com.restaurante.hexagonal.application.ports.output.OrderDetailRepositoryPort;
import com.restaurante.hexagonal.domain.model.OrderDetail;
import com.restaurante.hexagonal.infrastructure.persistence.entity.OrderDetailEntity;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.OrderDetailMapper;
import com.restaurante.hexagonal.infrastructure.persistence.repository.JpaOrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderDetailPersistenceAdapter implements OrderDetailRepositoryPort {

    private final JpaOrderDetailRepository repository;
    private final OrderDetailMapper mapper;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        OrderDetailEntity entity = mapper.toEntity(orderDetail);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<OrderDetail> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        OrderDetailEntity entity = mapper.toEntity(orderDetail);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}