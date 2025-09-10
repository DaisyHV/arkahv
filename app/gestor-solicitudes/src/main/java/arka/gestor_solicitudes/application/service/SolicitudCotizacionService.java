package arka.gestor_solicitudes.application.service;

import arka.gestor_solicitudes.domain.model.SolicitudCotizacion;
import reactor.core.publisher.Mono;

public interface SolicitudCotizacionService {

    Mono<SolicitudCotizacion> obtenerCotizacion(Integer cotizacionId);
    //double calcularImpuesto (double monto, double tasaImpuesto);
    //double calcularDomicilio (SolicitudCotizacion solicitudCotizacion );
}
