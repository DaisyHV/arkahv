package com.arka.arkahv.infraestructure.adapter.in.web.mapper;

import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.DetailOrderDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface DetailOrderWebMapper {
    DetailOrderWebMapper INSTANCE = Mappers.getMapper(DetailOrderWebMapper.class);

    @Mapping(source="idProduct", target="product_id")
    @Mapping(source="idOrder", target="order_id")
    @Mapping(source="number", target="numberProducts")
    //@Mapping(target = "order_id", ignore = true)
    DetailOrderDTO detailOrderTodetailOrderDTO(DetailOrder detailOrder);

    @IterableMapping(elementTargetType = DetailOrderDTO.class)
    List<DetailOrderDTO> detailOrderTodetailOrderDTOs(List<DetailOrder> detailOrders);

    @InheritInverseConfiguration
    DetailOrder detailOrderDTOToDetailOrder(DetailOrderDTO DetailOrderDTO);
}
