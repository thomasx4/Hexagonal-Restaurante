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

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductInputPort productInputPort;

    public ProductController(ProductInputPort productInputPort) {
        this.productInputPort = productInputPort;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
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

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productInputPort.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        return productInputPort.deleteProductById(id);
    }
}