package com.arka.arkahv.infraestructure.adapter.out.persistence.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comentarios")
public class ComentarioEntity {

    @Id
    private String id;
    private String productoId;
    private String usuario;
    private String texto;
    private int likes;
}
