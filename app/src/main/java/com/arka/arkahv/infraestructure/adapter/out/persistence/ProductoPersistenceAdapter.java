package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.out.ProductRepositoryPort;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ProductoPersistenceAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository repository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(producto -> productMapper.toDomain(producto))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllStock() {
        return repository.findAllByOrderByStockAsc().stream().limit(5)
                .map(producto -> productMapper.toDomain(producto))
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(int id) {
        if (repository.findById(id).isPresent()) {
            return productMapper.toDomain(repository.findById(id).get());
        } else {
            return null;
        }
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity savedEntity = repository.save(entity);
        return productMapper.toDomain(savedEntity);

    }

    @Override
    public Product update(Product product) {
        int id = product.getId();
        if(repository.findById(id).isPresent()){
            ProductEntity entity = productMapper.toEntity(product);
            ProductEntity updatedEntity = repository.save(entity);
            return productMapper.toDomain(updatedEntity);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        if(repository.findById(id).isPresent()){
        repository.deleteById(id);
    }}

    // Aquí puedes implementar los métodos del repositorio utilizando JPA o cualquier otra tecnología de persistencia.
    // Por ejemplo, podrías usar un EntityManager para realizar operaciones CRUD sobre la entidad Producto.

    // Ejemplo de método para encontrar un producto por ID:
    // public Optional<Product> findById(int id) {
    //     // Implementación aquí
    // }

    // Ejemplo de método para guardar un producto:
    // public Product save(Product product) {
    //     // Implementación aquí
    // }
}
