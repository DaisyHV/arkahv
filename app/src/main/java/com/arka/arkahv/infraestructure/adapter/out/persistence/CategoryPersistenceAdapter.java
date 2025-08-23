package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.port.out.CategoryRepositoryPort;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CategoryEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.CategoryMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {

    private final CategoryJpaRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<Category> findAll() {
        return repository.findAll().stream()
                .map(mapper::categoryEntityToCategory)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(int id) {
        return repository.findById(id)
                .map(mapper::categoryEntityToCategory);
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = mapper.categoryToCategoryEntity(category);
        CategoryEntity savedEntity = repository.save(entity);
        return mapper.categoryEntityToCategory(savedEntity);
    }



    @Override
    public void delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public boolean existsById(int id) {
        return repository.existsById(id);
    }
}
