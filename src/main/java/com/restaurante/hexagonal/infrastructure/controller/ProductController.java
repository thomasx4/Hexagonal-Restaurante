package com.restaurante.hexagonal.infrastructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.hexagonal.application.ports.input.ProductInputPort;
import com.restaurante.hexagonal.domain.model.Product;
import com.restaurante.hexagonal.infrastructure.controller.dto.ProductRequestDTO;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductInputPort productInputPort;

    public ProductController(ProductInputPort productInputPort) {
        this.productInputPort = productInputPort;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRequestDTO dto) {

        Product product = Product.create(
                dto.name,
                dto.description,
                dto.price,
                dto.categoryId,
                dto.available
        );

        return productInputPort.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productInputPort.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productInputPort.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {

        if (dto == null) {
            throw new RuntimeException("Request body is missing");
        }

        Product product = new Product.Builder()
                .id(id)
                .name(dto.name)
                .description(dto.description)
                .price(dto.price)
                .categoryId(dto.categoryId)
                .available(dto.available)
                .build();

        return productInputPort.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        return productInputPort.deleteProductById(id);
    }
}