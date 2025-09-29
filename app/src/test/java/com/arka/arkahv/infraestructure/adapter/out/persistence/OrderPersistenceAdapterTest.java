package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.Customer;
import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CustomerEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.DetailOrderEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.OrderEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.CustomerMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.OrderMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.CustomerJpaRepository;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.OrderJpaRepository;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;


public class OrderPersistenceAdapterTest {


    @Mock
    private OrderJpaRepository repository;

    @Mock
    private ProductJpaRepository repositoryProduct;

    @Mock
    private OrderMapper mapper;
    @Mock
    private CustomerMapper mapperCustomer;

    @Mock
    private CustomerJpaRepository customerRepository;

    @InjectMocks
    private OrderPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_returnsMappedOrders() {
        OrderEntity entity = new OrderEntity(1,100.000,null,null, null);
        Order order = new Order(1,100.000,null,1, null);
        when(repository.findAllWithDetailsAndProducts()).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(order);

        List<Order> result = adapter.findAll();

       assertEquals(1, result.size());
       assertEquals(order, result.get(0));
        //(()) assertTrue(result.isEmpty());
    }



    @Test
    void saveOrder_shouldPersistOrderWithDetailsSuccessfully() {
        // Arrange
        Date fecha = new Date();

        // Dominio
        Product product = new Product(1, "Teclado", 10, 100.0, null, null);
        DetailOrder detailOrder = new DetailOrder(1, product, null, 2);
        List<DetailOrder> detailOrders = List.of(detailOrder);
        Order order = new Order(1, 200.0, fecha, 1, detailOrders);

        // Entidades
        CustomerEntity customerEntity = new CustomerEntity(1, "Juan", "Perez", "123", "juan@mail.com", null);
        Customer customer = new Customer(1, "Juan", "Perez", "123", "juan@mail.com");

        ProductEntity productEntity = new ProductEntity(1, "Teclado", 10, 100.0, null, null);

        OrderEntity orderEntityToSave = new OrderEntity(); // puedes construir con constructor si tienes
        OrderEntity savedEntity = new OrderEntity();
        savedEntity.setId(1);
        savedEntity.setTotal(0);
        savedEntity.setDate_order(fecha);
        savedEntity.setCustomer(customerEntity);
        savedEntity.setDetails_order(new ArrayList<>()); // importante

        // Mocks
        when(customerRepository.findById(1)).thenReturn(Optional.of(customerEntity));
        when(mapperCustomer.customerEntityToCustomer(customerEntity)).thenReturn(customer);
        when(mapper.toEntity(order, customer)).thenReturn(orderEntityToSave);
        when(repositoryProduct.findById(1)).thenReturn(Optional.of(productEntity));
        when(repository.save(any(OrderEntity.class))).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(order);

        // Act
        Order result = adapter.saveOrder(order);

        // Assert
        assertNotNull(result);
        assertEquals(order.getTotal(), result.getTotal());
        assertEquals(order.getCustomer(), result.getCustomer());
        assertEquals(order.getDetails_order().size(), result.getDetails_order().size());
    }

/*
    @Test
    void delete_deletesIfExists() {
        when(repository.existsById(1)).thenReturn(true);

        adapter.deleteOrder(1);

        verify(repository).deleteById(1);
    }
*/



}
