package com.arkahv.cotizador.application.service;

import com.arkahv.cotizador.domain.model.Cotizacion;
import reactor.core.publisher.Mono;

public interface CotizacionService {

    Mono<Cotizacion> generarCotizacion(Cotizacion request);
    Mono<Cotizacion> obtenerCotizacion(Integer cotizacionId);
}
