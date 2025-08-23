package com.arka.arkahv.infraestructure.adapter.in.web.mapper;

import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.DetailOrderDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.OrderDTO;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {DetailOrderWebMapper.class})
public interface OrderWebMapper {

    @Mapping(source="date_order", target="date_order")
    @Mapping(source = "details_order", target = "details")
    OrderDTO orderToOrderDTO(Order order);
    /*
    default List<DetailOrderDTO> mapDetalles(List<DetailOrder> detalles) {
        if (detalles == null) {
            return null;
        }
        return detalles.stream()
                .map(detalle -> DetailOrderWebMapper.INSTANCE.detailOrderTodetailOrderDTO(detalle))
                .collect(Collectors.toList());
    }
*/
    //List<OrderDTO> ordersToOrdersDTO(List<Order> orders);
    //List<Order> ordersDTOToOrders(List<OrderDTO> ordersDTO);
    @InheritInverseConfiguration
    Order orderDTOToOrder(OrderDTO orderDTO);

}
