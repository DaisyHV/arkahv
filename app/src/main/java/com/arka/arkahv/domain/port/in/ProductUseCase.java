package com.arka.arkahv.domain.port.in;

import com.arka.arkahv.domain.model.Product;

import java.util.List;

public interface ProductUseCase {

    List<Product> getAllProducts();
    Product getProductById(int id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(int id);
}
