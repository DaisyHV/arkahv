package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.ProductMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class ProductoPersistenceAdapterTest {
    @Mock
    ProductJpaRepository repository;

    @Mock
    ProductMapper mapper;

    @InjectMocks
    ProductoPersistenceAdapter productoPersistenceAdapter;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void findAllProductsTest(){
        // Arrange: Preparación del entorno de prueba
        when(repository.findAll()).thenReturn(List.of(new ProductEntity(1,"Teclado",20,100.000,null,null)));
        when(mapper.toDomain(new ProductEntity(1,"Teclado",20,100.000,null,null))).thenReturn(new Product(1,"Teclado",20,100.000,null,null));
        // Act: Ejecución del método a probar
        List<Product> productos = productoPersistenceAdapter.findAll();
        // Assert: Verificación de los resultados
        assertFalse(productos.isEmpty());
        assertEquals(1, productos.size());

        // test que no pasaria porque solo hay un producto
        //assertEquals(2, productos.size());
    }

    @Test
    public void findByIdProductTest(){
    when(repository.findById(1)).thenReturn(Optional.of(new ProductEntity(1,"Teclado",20,100.000,null,null)));
        when(mapper.toDomain(new ProductEntity(1,"Teclado",20,100.000,null,null))).thenReturn(new Product(1,"Teclado",20,100.000,null,null));
        // Act: Ejecución del método a probar
        Product producto = productoPersistenceAdapter.findById(1);
        // Assert: Verificación de los resultados
        assertEquals(producto, new Product(1,"Teclado",20,100.000,null,null));
        assertEquals(1, producto);
    }

    @Test
    void save_persistsAndReturnsProduct() {
        Product product = new Product();
        ProductEntity entity = new ProductEntity();
        ProductEntity savedEntity = new ProductEntity();
        when(mapper.toEntity(product)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(product);

        Product result = productoPersistenceAdapter.save(product);

        assertEquals(product, result);
    }

    @Test
    void update_updatesAndReturnsProductIfExists() {
        Product product = new Product();
        product.setId(1);
        ProductEntity entity = new ProductEntity();
        ProductEntity updatedEntity = new ProductEntity();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toEntity(product)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(updatedEntity);
        when(mapper.toDomain(updatedEntity)).thenReturn(product);

        Product result = productoPersistenceAdapter.update(product);

        assertEquals(product, result);
    }


}
