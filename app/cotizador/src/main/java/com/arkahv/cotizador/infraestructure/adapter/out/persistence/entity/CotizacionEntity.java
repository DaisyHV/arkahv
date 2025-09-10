package com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity;


import com.arkahv.cotizador.domain.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("cotizaciones")

public class CotizacionEntity {

    @Id
    private int id;
    private String cliente;

    @Column("fechaSolicitud")
    private LocalDateTime fecha_solicitud;
    private String estado;
    private double total;
}
