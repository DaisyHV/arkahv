package com.arka.arkahv.infraestructure.adapter.out.persistence.repository;

import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {


}
