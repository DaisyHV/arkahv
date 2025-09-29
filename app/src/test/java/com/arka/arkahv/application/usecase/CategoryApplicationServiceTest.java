package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.port.out.CategoryRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryApplicationServiceTest {



        @Mock
        private CategoryRepositoryPort categoryRepositoryPort;

        @InjectMocks
        private CategoryApplicationService service;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void getAllCategories_returnsAllCategories() {
            List<Category> categories = Arrays.asList(
                    new Category(1, "Cat1"),
                    new Category(2, "Cat2")
            );
            when(categoryRepositoryPort.findAll()).thenReturn(categories);

            List<Category> result = service.getAllCategories();

            assertEquals(2, result.size());
            verify(categoryRepositoryPort).findAll();
        }

        @Test
        void getCategoryById_existingId_returnsCategory() {
            Category category = new Category(1, "Cat1");
            when(categoryRepositoryPort.findById(1)).thenReturn(Optional.of(category));

            Category result = service.getCategoryById(1);

            assertEquals(category, result);
            verify(categoryRepositoryPort).findById(1);
        }

        @Test
        void getCategoryById_nonExistingId_throwsException() {
            when(categoryRepositoryPort.findById(999)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                service.getCategoryById(999);
            });

            assertTrue(exception.getMessage().contains("Category not found with id: 999"));
            verify(categoryRepositoryPort).findById(999);
        }

        @Test
        void createCategory_savesCategory() {
            Category category = new Category(0, "New Category");
            Category savedCategory = new Category(1, "New Category");

            when(categoryRepositoryPort.save(category)).thenReturn(savedCategory);

            Category result = service.createCategory(category);

            assertEquals(savedCategory, result);
            verify(categoryRepositoryPort).save(category);
        }

        @Test
        void deleteCategory_existingId_deletesCategory() {
            int id = 1;
            when(categoryRepositoryPort.existsById(id)).thenReturn(true);

            service.deleteCategory(id);

            verify(categoryRepositoryPort).existsById(id);
            verify(categoryRepositoryPort).delete(id);
        }

        @Test
        void deleteCategory_nonExistingId_throwsException() {
            int id = 999;
            when(categoryRepositoryPort.existsById(id)).thenReturn(false);

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                service.deleteCategory(id);
            });

            assertTrue(exception.getMessage().contains("Category not found with id: " + id));
            verify(categoryRepositoryPort).existsById(id);
            verify(categoryRepositoryPort, never()).delete(anyInt());
        }
    }

