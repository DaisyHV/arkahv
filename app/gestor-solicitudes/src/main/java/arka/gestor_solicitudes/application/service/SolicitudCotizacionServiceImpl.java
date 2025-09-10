package arka.gestor_solicitudes.application.service;

import arka.gestor_solicitudes.domain.model.SolicitudCotizacion;
import arka.gestor_solicitudes.infraestructure.adapter.out.persistence.SolicitudCotizacionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SolicitudCotizacionServiceImpl implements SolicitudCotizacionService {

    private final SolicitudCotizacionAdapter adapter;
    @Override
    public Mono<SolicitudCotizacion> obtenerCotizacion(Integer cotizacionId) {
        return adapter.obtenerCotizacion(cotizacionId);
    }





   /* @Override
    public double calcularDomicilio(SolicitudCotizacion solicitudCotizacion) {
        String ciudad = solicitudCotizacion.getCiudad();
        double total = solicitudCotizacion.getTotal();

        // Regla 1: si la ciudad es "Bogotá", el domicilio es más caro
        if ("Bogotá".equalsIgnoreCase(solicitudCotizacion.getCiudad())) {
            return 15000.0;
        }

        // Regla 2: si el total supera $50,000, el domicilio es gratis
        if (total > 50000.0) {
            return 0.0;
        }

        // Regla por defecto
        return 8000.0;
    }*/


        }


