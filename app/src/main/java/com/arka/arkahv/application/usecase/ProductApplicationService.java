package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.in.ProductUseCase;
import com.arka.arkahv.domain.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class ProductApplicationService implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;



    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepositoryPort.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepositoryPort.update(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepositoryPort.delete(id);
    }
}
