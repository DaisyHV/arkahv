package com.arka.arkahv.infraestructure.adapter.in.web;

import com.arka.arkahv.domain.model.Comentario;
import com.arka.arkahv.domain.port.in.ComentarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bff/comentarios")
@RequiredArgsConstructor
public class ComentarioBffController {
    private final ComentarioUseCase comentarioUseCase;

    // ✅ Endpoint para cliente web: obtener todos los comentarios
    @GetMapping("/web/{productoId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosWeb(@PathVariable String productoId) {
        List<Comentario> comentarios = comentarioUseCase.obtenerComentariosPorProducto10(productoId);
        return ResponseEntity.ok(comentarios);
    }

    // ✅ Endpoint para cliente web: obtener todos los comentarios
    @GetMapping("/mobile/{productoId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosMobile(@PathVariable String productoId) {
        List<Comentario> comentarios = comentarioUseCase.obtenerComentariosPorProducto5(productoId);
        return ResponseEntity.ok(comentarios);
    }

}
