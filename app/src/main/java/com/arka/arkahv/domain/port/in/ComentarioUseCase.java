package com.arka.arkahv.domain.port.in;
import java.util.List;

import com.arka.arkahv.domain.model.Comentario;

public interface ComentarioUseCase {

    void guardarComentario(Comentario comentario);
    List<Comentario> obtenerComentariosPorProducto10(String productoId);

    List<Comentario> obtenerComentariosPorProducto5(String productoId);
}
