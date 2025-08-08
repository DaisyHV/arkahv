package com.arka.arkahv.infraestructure.adapter.in.web.mapper;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.CategoryDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {

    //@Mapping(source="category.description", target="categoryName")
    @Mapping(source="description", target="name")
    CategoryDTO categoryToCategoryDTO(Category category);

    List<CategoryDTO> categoriesToCategoriesDTO(List<Category> categories);

    @InheritInverseConfiguration
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
