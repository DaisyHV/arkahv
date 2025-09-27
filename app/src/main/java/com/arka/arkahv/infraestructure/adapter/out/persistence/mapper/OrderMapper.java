package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import com.arka.arkahv.domain.model.Customer;
import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CustomerEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.OrderEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.DetailOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class OrderMapper {

    private ProductMapper productMapper;
    private CustomerMapper customerMapper;
    //crear customer repository

    public OrderMapper(ProductMapper productMapper, CustomerMapper customerMapper) {
        this.productMapper = productMapper;
        this.customerMapper = customerMapper;
    }

    public OrderEntity toEntity(Order order, Customer customer) {
        if (order == null) return null;
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setDate_order(order.getDate_order());
        entity.setTotal(order.getTotal());
        CustomerEntity customerEntity = customerMapper.customerToCustomerEntity(customer);
        entity.setCustomer(customerEntity);
        // Mapea los detalles, pero sin setear el campo order en cada detalle para evitar ciclos
        if (order.getDetails_order() != null) {
            entity.setDetails_order(order.getDetails_order().stream()
                    .map(this::toDetailOrderEntity)
                    .collect(Collectors.toList()));
        }
        return entity;
    }

    public DetailOrder toDetailOrder(DetailOrderEntity entity) {
        if (entity == null) return null;
        DetailOrder detailOrder = new DetailOrder();
        detailOrder.setId(entity.getId());
        detailOrder.setNumber(entity.getNumber());
        // No setees detailOrder.setOrder(...) aquí para evitar recursión
        // Puedes mapear el producto si es necesario
        detailOrder.setProduct(entity.getProduct() != null ? productMapper.toDomain(entity.getProduct()) : null);
        return detailOrder;
    }

    public Order toDomain(OrderEntity entity) {
        if (entity == null) return null;
        Order order = new Order();
        order.setId(entity.getId());
        order.setDate_order(entity.getDate_order());
        order.setCustomer(entity.getCustomer().getId());
        // Mapea los detalles, pero sin setear el campo order en cada detalle para evitar ciclos
        if (entity.getDetails_order() != null) {
            order.setDetails_order(entity.getDetails_order().stream()
                    .map(this::toDetailOrder)
                    .collect(Collectors.toList()));
        }
        return order;
    }

    public DetailOrderEntity toDetailOrderEntity(DetailOrder detailOrder) {
        if (detailOrder == null) return null;
        DetailOrderEntity entity = new DetailOrderEntity();
        entity.setId(detailOrder.getId());
        entity.setNumber(detailOrder.getNumber());
        // No setees entity.setOrder(...) aquí para evitar recursión
        entity.setProduct(detailOrder.getProduct() != null ? productMapper.toEntity(detailOrder.getProduct()) : null);
        return entity;
    }






}
