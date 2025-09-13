package arka.gestor_solicitudes.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CalculoEnvio {
    private String id;
    private String origen;
    private String destino;
    private BigDecimal peso;
    private String dimensiones;
    private BigDecimal costo;
    private Integer tiempoEstimadoDias;
    private EstadoCalculo estado;
    private LocalDateTime fechaCalculo;
    private String proveedorUtilizado;
    private String mensajeError;

    public CalculoEnvio() {}

    public CalculoEnvio(String origen, String destino, BigDecimal peso, String dimensiones) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.estado = EstadoCalculo.PENDIENTE;
        this.fechaCalculo = LocalDateTime.now();
    }

    // Métodos de conveniencia
    public static CalculoEnvio exitoso(String id, BigDecimal costo, Integer tiempoEstimado, String proveedor) {
        CalculoEnvio calculo = new CalculoEnvio();
        calculo.id = id;
        calculo.costo = costo;
        calculo.tiempoEstimadoDias = tiempoEstimado;
        calculo.estado = EstadoCalculo.COMPLETADO;
        calculo.proveedorUtilizado = proveedor;
        calculo.fechaCalculo = LocalDateTime.now();
        return calculo;
    }

    public static CalculoEnvio fallback(String mensaje) {
        CalculoEnvio calculo = new CalculoEnvio();
        calculo.estado = EstadoCalculo.FALLBACK;
        calculo.mensajeError = mensaje;
        calculo.costo = BigDecimal.valueOf(50.0); // Costo por defecto
        calculo.tiempoEstimadoDias = 7; // Tiempo por defecto
        calculo.proveedorUtilizado = "SERVICIO_BACKUP";
        calculo.fechaCalculo = LocalDateTime.now();
        return calculo;
    }

    public static CalculoEnvio error(String mensaje) {
        CalculoEnvio calculo = new CalculoEnvio();
        calculo.estado = EstadoCalculo.ERROR;
        calculo.mensajeError = mensaje;
        calculo.fechaCalculo = LocalDateTime.now();
        return calculo;
    }

}
