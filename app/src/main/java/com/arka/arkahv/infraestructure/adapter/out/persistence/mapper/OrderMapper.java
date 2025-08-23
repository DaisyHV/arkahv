package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.domain.model.Order;

import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.DetailOrderEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.TargetType;

import java.util.List;
import java.util.stream.Collectors;



@Mapper(componentModel = "spring")
public interface OrderMapper {
    // Mapping methods for Order and OrderEntity
    @Mapping(source = "details_order", target = "details_order")
    OrderEntity  orderToOrderEntity(Order order);


    default List<DetailOrderEntity> mapDetalles(List<DetailOrder> detalles) {
        if (detalles == null) {
            return null;
        }
        return detalles.stream().map(detalle -> DetailorderMapper.INSTANCE.detailOrderTodetailOrderEntity(detalle))
                .collect(Collectors.toList());
    }
    List<OrderEntity> ordersToOrderEntities(List<Order> orders);
    List<Order> orderEntitiesToOrders(List<OrderEntity> ordersEntity);
   // @InheritInverseConfiguration
    Order orderEntityToOrder(OrderEntity orderEntity);
}
