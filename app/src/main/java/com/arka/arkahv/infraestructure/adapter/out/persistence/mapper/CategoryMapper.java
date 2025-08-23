package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.CategoryDTO;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //@Mapping(source="category.description", target="categoryName")
    //@Mapping(source="description", target="Name")
    @Mapping(target = "products", ignore = true)
    CategoryEntity categoryToCategoryEntity(Category category);

    List<CategoryEntity> categoriesToCategoriesEntity(List<Category> categories);
    List<Category> categoriesEntityToCategories(List<CategoryEntity> categories);

    @InheritInverseConfiguration
    Category categoryEntityToCategory(CategoryEntity categoryEntity);

}
