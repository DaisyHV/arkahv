package com.arka.arkahv.infraestructure.adapter.out.persistence.repository;

import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("""
    SELECT DISTINCT o FROM OrderEntity o
    LEFT JOIN FETCH o.details_order d
    LEFT JOIN FETCH d.product
""")
    List<OrderEntity> findAllWithDetailsAndProducts();
}
