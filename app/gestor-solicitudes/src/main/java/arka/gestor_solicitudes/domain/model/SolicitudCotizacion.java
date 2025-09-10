package arka.gestor_solicitudes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCotizacion {

    private int id;
    private String cliente;
    private LocalDateTime fecha_solicitud;
    private List<ProductoCotizado> productos;
    private String estado;
    //private String ciudad;
    private double total;
    private double impuesto;
    //private double domicilio;
    private double totalFinal;
}

