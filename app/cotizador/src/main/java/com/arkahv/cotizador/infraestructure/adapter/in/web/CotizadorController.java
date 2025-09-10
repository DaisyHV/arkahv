package com.arkahv.cotizador.infraestructure.adapter.in.web;

import com.arkahv.cotizador.application.service.CotizacionService;
import com.arkahv.cotizador.domain.mapper.CotizacionMapper;
import com.arkahv.cotizador.domain.model.Cotizacion;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.CotizacionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.EnumUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/cotizaciones")
@RequiredArgsConstructor
public class CotizadorController {

    private final CotizacionService cotizacionService;
    private final CotizacionMapper cotizacionMapper;

    @PostMapping
    public Mono<Cotizacion> crear(@RequestBody Cotizacion cotizacion) {
        return cotizacionService.generarCotizacion(cotizacion);

    }


/*
    @GetMapping
    public Flux<Cotizacion> listar() {
        //return cotizacionService.listarCotizaciones();
        return null;
    }*/

    @GetMapping
    public String hello() {
        return "Hola desde el servicio de cotizaciones";
    }

    @GetMapping("/{id}")
    public Mono<Cotizacion> obtener(@PathVariable Integer id) {
    return cotizacionService.obtenerCotizacion(id);
    }


}
