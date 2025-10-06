package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.Comentario;
import java.util.List;
public interface ComentarioRepositoryPort {
    void guardarComentario(Comentario comentario);
    List<Comentario> obtenerComentariosPorProducto10(String productoId);

    List<Comentario> obtenerComentariosPorProducto5(String productoId);
}
