package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.out.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductApplicationServiceTest {


        @Mock
        private ProductRepositoryPort productRepositoryPort;

        @InjectMocks
        private ProductApplicationService service;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void getAllProducts_returnsAllProducts() {
            List<Product> products = Arrays.asList(
                    new Product(1, "Prod1",10,100.0,null,null),
                    new Product(2, "Prod2",20,200.0,null,null)
            );
            when(productRepositoryPort.findAll()).thenReturn(products);

            List<Product> result = service.getAllProducts();

            assertEquals(2, result.size());
            verify(productRepositoryPort).findAll();
        }

        @Test
        void getProductById_returnsProduct() {
            Product product = new Product(1, "Prod1",10,100.0,null,null);
            when(productRepositoryPort.findById(1)).thenReturn(product);

            Product result = service.getProductById(1);

            assertEquals(product, result);
            verify(productRepositoryPort).findById(1);
        }

        @Test
        void createProduct_savesAndReturnsProduct() {
            Product product = new Product(0, "NewProduct",10,100.0,null,null);
            Product savedProduct = new Product(1, "NewProduct",10,100.0,null,null);

            when(productRepositoryPort.save(product)).thenReturn(savedProduct);

            Product result = service.createProduct(product);

            assertEquals(savedProduct, result);
            verify(productRepositoryPort).save(product);
        }

        @Test
        void updateProduct_updatesAndReturnsProduct() {
            Product product = new Product(1, "UpdatedProduct",10,100.0,null,null);

            when(productRepositoryPort.update(product)).thenReturn(product);

            Product result = service.updateProduct(product);

            assertEquals(product, result);
            verify(productRepositoryPort).update(product);
        }

        @Test
        void deleteProduct_deletesProduct() {
            int id = 1;

            service.deleteProduct(id);

            verify(productRepositoryPort).delete(id);
        }
    }

