package arka.gestor_solicitudes.infraestructure.adapter.in.web;

import arka.gestor_solicitudes.application.service.SolicitudCotizacionService;
import arka.gestor_solicitudes.application.service.SolicitudCotizacionServiceImpl;
import arka.gestor_solicitudes.domain.model.SolicitudCotizacion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/solicitudCotizacion")
@RequiredArgsConstructor
public class SolicitudCotizacionController {

    private final SolicitudCotizacionServiceImpl service;

    @GetMapping("/{id}")
    public Mono<SolicitudCotizacion> obtener(@PathVariable Integer id) {
        return service.obtenerCotizacion(id);
    }

    @GetMapping("/generarOrden/{id}")
    public Mono<String> generarOrden(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {

        return service.generarOrdenEfectuada(id, authorizationHeader);
    }
}
