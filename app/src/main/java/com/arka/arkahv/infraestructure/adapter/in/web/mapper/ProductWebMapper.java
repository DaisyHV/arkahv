package com.arka.arkahv.infraestructure.adapter.in.web.mapper;


import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {



    @Mapping(source="category.id", target="categoriaId")
    @Mapping(source="description", target="name")
    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productsToProductDTOs(List<Product> products);

    @InheritInverseConfiguration
    Product productDTOToProduct(ProductDTO productDTO);
}
