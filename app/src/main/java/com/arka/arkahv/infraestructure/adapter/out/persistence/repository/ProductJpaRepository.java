package com.arka.arkahv.infraestructure.adapter.out.persistence.repository;

import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByOrderByStockAsc();
}
