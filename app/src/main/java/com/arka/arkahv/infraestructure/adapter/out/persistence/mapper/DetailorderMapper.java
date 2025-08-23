package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.DetailOrderDTO;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.DetailOrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetailorderMapper {
    DetailorderMapper INSTANCE = Mappers.getMapper(DetailorderMapper.class);


    @Mapping(source="number", target="number")
    @Mapping(source="product.id", target="product.id")
    @Mapping(source="order.id", target="order.id")

    DetailOrderEntity detailOrderTodetailOrderEntity(DetailOrder detailOrder);

     @IterableMapping(elementTargetType = DetailOrderEntity.class)
     List<DetailOrderEntity> detailOrderTodetailOrdersEntity(List<DetailOrder> detailOrders);

    @InheritInverseConfiguration
    DetailOrder detailOrderEntityToDetailOrder(DetailOrderEntity detailOrderEntity);


}
