package com.arka.arkahv.infraestructure.adapter.out.persistence.repository;

import com.arka.arkahv.domain.model.Comentario;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ComentarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComentarioRepositorio extends MongoRepository<ComentarioEntity, String> {
    List<ComentarioEntity> findByProductoId(String productoId);
}
