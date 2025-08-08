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
public interface DetailOrderMapper {
    DetailOrderMapper INSTANCE = Mappers.getMapper(DetailOrderMapper.class);

    @Mapping(source="product.id", target="product_id")
   @Mapping(source="order.id", target="order_id")
    @Mapping(source="number", target="numberProducts")
    DetailOrderDTO detailOrderTodetailOrderDTO(DetailOrder detailOrder);

    @IterableMapping(elementTargetType = DetailOrderDTO.class)
    List<DetailOrderDTO> detailOrderTodetailOrderDTOs(List<DetailOrder> detailOrders);

    @InheritInverseConfiguration
    DetailOrder detailOrderDTOToDetailOrder(DetailOrderDTO DetailOrderDTO);

}
