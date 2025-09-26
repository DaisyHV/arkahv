package com.arka.arkahv.infraestructure.adapter.out.persistence.repository;

import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CategoryEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
}
