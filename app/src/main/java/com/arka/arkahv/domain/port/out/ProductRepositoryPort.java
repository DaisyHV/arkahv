package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    List<Product> findAll();
    // Optional<Product> findById(int id); // Optional para evitar NullPointerException
    Product findById(int id);
    Product save(Product product);
    Product update(Product product);
    void delete(int id);
}
