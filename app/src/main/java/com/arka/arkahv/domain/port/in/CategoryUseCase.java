package com.arka.arkahv.domain.port.in;

import com.arka.arkahv.domain.model.Category;

import java.util.List;

public interface CategoryUseCase {


     List<Category> getAllCategories();
     Category getCategoryById(int id);
     Category createCategory(Category category);
     //Category updateCategory(Category category);
     void deleteCategory(int id);
}
