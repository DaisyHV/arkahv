package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.in.OrderUseCase;
import com.arka.arkahv.domain.port.out.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderApplicationService implements OrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public List<Order> getAllOrders() {
        return orderRepositoryPort.findAll();
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepositoryPort.findOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepositoryPort.saveOrder(order);
    }



    @Override
    public void deleteOrder(int id) {
        if (!orderRepositoryPort.findOrderById(id).isPresent()) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepositoryPort.deleteOrder(id);
    }
}
