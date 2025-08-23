package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // @Mapping(source="category.description", target="categoryName")
    ProductEntity productToProductEntity(Product product);

    List<ProductEntity> productsToProductEntitys(List<Product> products);

    @InheritInverseConfiguration
    Product productEntityToProduct(ProductEntity productEntity);
}
