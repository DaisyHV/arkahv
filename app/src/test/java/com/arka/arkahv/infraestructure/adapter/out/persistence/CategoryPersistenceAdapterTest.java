package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CategoryEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.CategoryMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.ProductMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryPersistenceAdapterTest {
    @Mock
    CategoryJpaRepository repository;

    @Mock
    CategoryMapper mapper;

    @InjectMocks
    CategoryPersistenceAdapter adapter;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findAll_returnsMappedCategories() {
        CategoryEntity entity = new CategoryEntity(1, "Teclados", null);
        Category category = new Category();
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.categoryEntityToCategory(entity)).thenReturn(category);

        List<Category> result = adapter.findAll();

        assertEquals(1, result.size());
        assertEquals(category, result.get(0));
    }

    @Test
    void findById_returnsCategoryIfExists() {
        CategoryEntity entity = new CategoryEntity(1, "Teclados", null);
        Category category = new Category();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.categoryEntityToCategory(entity)).thenReturn(category);

        Optional<Category> result = adapter.findById(1);

        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void save_persistsAndReturnsCategory() {
        Category category = new Category();
        CategoryEntity entity = new CategoryEntity();
        CategoryEntity savedEntity = new CategoryEntity();
        when(mapper.categoryToCategoryEntity(category)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.categoryEntityToCategory(savedEntity)).thenReturn(category);

        Category result = adapter.save(category);

        assertEquals(category, result);
    }

    @Test
    void delete_deletesIfExists() {
        when(repository.existsById(1)).thenReturn(true);

        adapter.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    void existsById_returnsTrueIfExists() {
        when(repository.existsById(1)).thenReturn(true);

        boolean result = adapter.existsById(1);

        assertTrue(result);
    }

    @Test
    void existsById_returnsFalseIfNotExists() {
        when(repository.existsById(2)).thenReturn(false);

        boolean result = adapter.existsById(2);

        assertFalse(result);
    }


}
