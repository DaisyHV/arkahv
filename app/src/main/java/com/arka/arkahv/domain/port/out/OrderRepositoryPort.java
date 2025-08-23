package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.Order;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


public interface OrderRepositoryPort {

    List<Order> findAll();
    Optional<Order> findOrderById(int id);
    Order saveOrder(Order order);
    //Order updateOrder(Order order);
    void deleteOrder(int id);
    List<Order> findOrdersByUserId(int userId);

}
