package com.arkahv.cotizador.infraestructure.adapter.out.persistence.repository;

import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.CotizacionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CotizacionRepository extends ReactiveCrudRepository<CotizacionEntity, Integer> {
}
