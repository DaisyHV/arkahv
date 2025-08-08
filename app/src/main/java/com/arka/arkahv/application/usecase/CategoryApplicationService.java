package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.port.in.CategoryUseCase;
import com.arka.arkahv.domain.port.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryApplicationService implements CategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepositoryPort.findAll();
    }

    @Override
    public Category getCategoryById(int id) {

        return categoryRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepositoryPort.save(category);
    }



    @Override
    public void deleteCategory(int id) {
        if (!categoryRepositoryPort.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepositoryPort.delete(id);

    }
}
