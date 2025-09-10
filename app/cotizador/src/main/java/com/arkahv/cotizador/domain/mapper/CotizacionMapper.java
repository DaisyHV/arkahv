package com.arkahv.cotizador.domain.mapper;

import com.arkahv.cotizador.domain.model.Cotizacion;
import com.arkahv.cotizador.domain.model.Producto;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.CotizacionEntity;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.ProductoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CotizacionMapper {


    private final ProductMapper productMapper;

    public  Cotizacion toDomain(CotizacionEntity cotizacionEntity, List<ProductoEntity> productosEntity) {
        List<Producto> productos = productosEntity.stream()
                .map(pe -> productMapper.toDomain(pe))
                .collect(Collectors.toList());

        return new Cotizacion(
                cotizacionEntity.getId(),
                cotizacionEntity.getCliente(),
                cotizacionEntity.getFecha_solicitud(),
                productos,
                cotizacionEntity.getEstado(),
                cotizacionEntity.getTotal()
        );
    }

    public static CotizacionEntity toEntity(Cotizacion cotizacion) {
        return new CotizacionEntity(
                cotizacion.getId(),
                cotizacion.getCliente(),
                cotizacion.getFecha_solicitud(),
                cotizacion.getEstado(),
                cotizacion.getTotal()
        );
    }

    public static List<ProductoEntity> toProductoEntityList(Cotizacion cotizacion) {
        return cotizacion.getProductos().stream()
                .map(p -> {
                    ProductoEntity pe = new ProductoEntity();
                    pe.setId(p.getId());
                    pe.setIdCotizacion(cotizacion.getId()); // importante para vincular
                    pe.setNombre(p.getNombre());
                    pe.setPrecio(p.getPrecio());
                    pe.setCantidad(p.getCantidad());
                    return pe;
                })
                .collect(Collectors.toList());
    }
}


