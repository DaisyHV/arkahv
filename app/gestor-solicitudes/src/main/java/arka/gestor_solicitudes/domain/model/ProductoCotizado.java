package arka.gestor_solicitudes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCotizado {

    private int id;
    private int producto;
    private String nombre;
    private double precio;
    private int cantidad;

}
