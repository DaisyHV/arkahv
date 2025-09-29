package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.domain.port.out.OrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderApplicationServiceTest {



        @Mock
        private OrderRepositoryPort orderRepositoryPort;

        @InjectMocks
        private OrderApplicationService service;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void getAllOrders_returnsAllOrders() {
            Date fecha = new Date();
            List<Order> orders = Arrays.asList(
                    new Order(1, 100.000,fecha,1,null),
                    new Order(1, 100.000,fecha,1,null)
            );
            when(orderRepositoryPort.findAll()).thenReturn(orders);

            List<Order> result = service.getAllOrders();

            assertEquals(2, result.size());
            verify(orderRepositoryPort).findAll();
        }

        @Test
        void getOrderById_existingId_returnsOrder() {
            Date fecha = new Date();
            Order order = new Order(1, 100.000,fecha,1,null);
            when(orderRepositoryPort.findOrderById(1)).thenReturn(Optional.of(order));

            Order result = service.getOrderById(1);

            assertEquals(order, result);
            verify(orderRepositoryPort).findOrderById(1);
        }

        @Test
        void getOrderById_nonExistingId_throwsException() {
            when(orderRepositoryPort.findOrderById(999)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                service.getOrderById(999);
            });

            assertTrue(exception.getMessage().contains("Order not found with id: 999"));
            verify(orderRepositoryPort).findOrderById(999);
        }

        @Test
        void createOrder_savesAndReturnsOrder() {
            Date fecha = new Date();
            Order order = new Order(1, 100.000,fecha,1,null);
            Order savedOrder = new Order(1, 100.000,fecha,1,null);

            when(orderRepositoryPort.saveOrder(order)).thenReturn(savedOrder);

            Order result = service.createOrder(order);

            assertEquals(savedOrder, result);
            verify(orderRepositoryPort).saveOrder(order);
        }

        @Test
        void deleteOrder_existingId_deletesOrder() {
            Date fecha = new Date();
            int id = 1;
            Order order = new Order(1, 100.000,fecha,1,null);

            when(orderRepositoryPort.findOrderById(id)).thenReturn(Optional.of(order));

            service.deleteOrder(id);

            verify(orderRepositoryPort).findOrderById(id);
            verify(orderRepositoryPort).deleteOrder(id);
        }

        @Test
        void deleteOrder_nonExistingId_throwsException() {
            int id = 999;

            when(orderRepositoryPort.findOrderById(id)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                service.deleteOrder(id);
            });

            assertTrue(exception.getMessage().contains("Order not found with id: " + id));
            verify(orderRepositoryPort).findOrderById(id);
            verify(orderRepositoryPort, never()).deleteOrder(anyInt());
        }
    }

