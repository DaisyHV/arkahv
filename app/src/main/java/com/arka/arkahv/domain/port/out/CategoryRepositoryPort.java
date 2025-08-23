package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {

    List<Category> findAll();
    Optional<Category> findById(int id);
    Category save(Category category);
    //Category update(Category category);
    void delete(int id);
    boolean existsById(int id);
}
