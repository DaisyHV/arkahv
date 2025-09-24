package com.arka.arkahv.infraestructure.adapter.out.persistence.repository;

import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends CrudRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByToken(String token);
    @Modifying
    int deleteByUsuario(UserEntity usuario);
}
