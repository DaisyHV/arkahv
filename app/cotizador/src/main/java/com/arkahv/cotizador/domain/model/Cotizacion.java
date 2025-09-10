package com.arkahv.cotizador.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cotizacion {
    private int id;
    private String cliente;
    private LocalDateTime fecha_solicitud;
    private List<Producto> productos;
    private String estado;
    private double total;
}
