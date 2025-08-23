package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.out.OrderRepositoryPort;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.DetailOrderEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.OrderEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.OrderMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.OrderJpaRepository;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository repository;
    private final ProductJpaRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {

        List<OrderEntity> orders = repository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        try{
            String json = mapper.writeValueAsString(orders);
            log.info("Objeto en JSON: {}", json);
        } catch (Exception e) {
            log.error("Error al configurar ObjectMapper: {}", e.getMessage());
        }


        return  orders
                .stream()
                .map( factura -> orderMapper.orderEntityToOrder(factura))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        return Optional.empty();
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        List<DetailOrderEntity> detalles = new ArrayList<>();
        for (DetailOrder detail : order.getDetails_order()) {
            ProductEntity product = productRepository.findById(detail.getIdProduct())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detail.getIdProduct()));
            DetailOrderEntity detailEntity = new DetailOrderEntity();
            detailEntity.setProduct(product);
            detailEntity.setNumber(detail.getNumber());
            detailEntity.setOrder(orderEntity); // establece relación bidireccional si aplica

            detalles.add(detailEntity);
        }
        orderEntity.setDetails_order(detalles);
        OrderEntity savedEntity = repository.save(orderEntity);
        return orderMapper.orderEntityToOrder(savedEntity);
        /*
        OrderEntity entity = orderMapper.orderToOrderEntity(order);

        // Por cada detalle, debes cargar el ProductEntity desde la DB
        for (DetailOrderEntity detail : entity.getDetails_order()) {
            Integer productId = detail.getProduct().getId();

            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));

            detail.setProduct(product);
            detail.setOrder(entity); // establece relación bidireccional si aplica
        }*/

        //return orderMapper.orderEntityToOrder(repository.save(entity));
    }



    @Override
    public void deleteOrder(int id) {

    }

    @Override
    public List<Order> findOrdersByUserId(int userId) {
        return List.of();
    }
}
