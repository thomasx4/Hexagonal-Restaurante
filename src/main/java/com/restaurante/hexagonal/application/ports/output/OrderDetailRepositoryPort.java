package com.restaurante.hexagonal.application.ports.output;

import com.restaurante.hexagonal.domain.model.OrderDetail;

import java.util.List;
import java.util.Optional;
public interface OrderDetailRepositoryPort {
    
    OrderDetail save(OrderDetail orderDetail);

    Optional<OrderDetail> findById(Long id);

    List<OrderDetail> findAll();

    OrderDetail update(OrderDetail orderDetail);

    void deleteById(Long id);

}






    