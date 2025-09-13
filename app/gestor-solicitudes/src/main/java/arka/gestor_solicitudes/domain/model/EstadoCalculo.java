package arka.gestor_solicitudes.domain.model;

public enum EstadoCalculo {

    PENDIENTE("Pendiente de cálculo"),
    PROCESANDO("Procesando cálculo"),
    COMPLETADO("Cálculo completado exitosamente"),
    FALLBACK("Usando valores por defecto debido a fallo del servicio"),
    ERROR("Error en el cálculo"),
    TIMEOUT("Timeout en el servicio externo");

    private final String descripcion;

    EstadoCalculo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
