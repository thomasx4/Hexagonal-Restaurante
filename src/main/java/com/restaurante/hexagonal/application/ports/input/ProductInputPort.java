package com.restaurante.hexagonal.application.ports.input;

import java.util.List;
import com.restaurante.hexagonal.domain.model.Product;

public interface ProductInputPort {
    Product createProduct(Product product);
    List<Product> getProducts();
    Product getProductById(Long id);
    Product deleteProductById(Long id);
    Product updateProduct(Long id, Product product);
}
