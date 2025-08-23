package com.arka.arkahv.infraestructure.adapter.out.persistence.repository;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CategoryEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {
}
