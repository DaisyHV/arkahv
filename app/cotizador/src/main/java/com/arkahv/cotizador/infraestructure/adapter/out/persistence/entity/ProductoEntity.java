package com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("productos")
public class ProductoEntity {

    @Id
    private int id;
    @Column("idCotizacion")
    private int idCotizacion;
    private int producto;
    private String nombre;
    private double precio;
    private int cantidad;
}
