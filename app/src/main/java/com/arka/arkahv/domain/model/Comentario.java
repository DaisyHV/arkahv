package com.arka.arkahv.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {
    private String id;

    private String productoId;
    private String usuario;
    private String texto;
    private int likes;
}
