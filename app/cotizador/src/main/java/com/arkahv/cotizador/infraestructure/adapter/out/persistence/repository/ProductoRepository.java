package com.arkahv.cotizador.infraestructure.adapter.out.persistence.repository;

import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductoRepository extends ReactiveCrudRepository<ProductoEntity, Integer> {

    Flux<ProductoEntity> findByIdCotizacion(Integer idCotizacion);
}
