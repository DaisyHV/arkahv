package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.Comentario;
import com.arka.arkahv.domain.port.in.ComentarioUseCase;
import com.arka.arkahv.domain.port.out.CategoryRepositoryPort;
import com.arka.arkahv.domain.port.out.ComentarioRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ComentarioApplicationService implements ComentarioUseCase {

    private final ComentarioRepositoryPort comentarioRepositoryPort;

    @Override
    public void guardarComentario(Comentario comentario) {
        comentarioRepositoryPort.guardarComentario(comentario);
    }

    @Override
    public List<Comentario> obtenerComentariosPorProducto10(String productoId) {
        return comentarioRepositoryPort.obtenerComentariosPorProducto10(productoId);
    }

    @Override
    public List<Comentario> obtenerComentariosPorProducto5(String productoId) {
        return comentarioRepositoryPort.obtenerComentariosPorProducto5(productoId);
    }
}
