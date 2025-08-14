package com.arka.arkahv.domain.port.in;

import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.domain.model.Product;

import java.util.List;

public interface OrderUseCase {
    List<Order> getAllOrders();
    Order getOrderById(int id);
    Order createOrder(Order order);
    // Order updateOrder(Order order);
    void deleteOrder(int id);
}
