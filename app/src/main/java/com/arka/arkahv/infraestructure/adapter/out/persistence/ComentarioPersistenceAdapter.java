package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.Comentario;
import com.arka.arkahv.domain.port.out.ComentarioRepositoryPort;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CategoryEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ComentarioEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.CategoryMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.ComentarioMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.ComentarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ComentarioPersistenceAdapter implements ComentarioRepositoryPort {

    private final ComentarioRepositorio repository;
    private final ComentarioMapper mapper;
    @Override
    public void guardarComentario(Comentario comentario) {
        ComentarioEntity entity = mapper.comentarioToComentarioEntity(comentario);
        ComentarioEntity savedEntity = repository.save(entity);

    }

    @Override
    public List<Comentario> obtenerComentariosPorProducto10(String productoId) {
        List<ComentarioEntity> comentarios = repository.findByProductoId(productoId);
        return repository.findByProductoId(productoId).stream()
                .limit(10)
                .map(mapper::comentarioEntityToComentario)
                .toList();
    }

    @Override
    public List<Comentario> obtenerComentariosPorProducto5(String productoId) {
        return repository.findByProductoId(productoId).stream()
                .limit(5)
                .map(mapper::comentarioEntityToComentario)
                .toList();
    }
}
