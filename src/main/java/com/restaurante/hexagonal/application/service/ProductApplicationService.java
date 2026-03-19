package com.restaurante.hexagonal.application.service;

import com.restaurante.hexagonal.domain.model.Product;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.application.ports.input.ProductInputPort;
import com.restaurante.hexagonal.application.ports.output.ProductRepositoryPort;

@Component
@Service
public class ProductApplicationService implements ProductInputPort {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductApplicationService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepositoryPort.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepositoryPort.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id ));
    }

    @Override
    public Product deleteProductById(Long id) {
        Product product = getProductById(id);
        productRepositoryPort.deleteById(id);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepositoryPort.save(product);
    }

}
