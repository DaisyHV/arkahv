package com.arkahv.cotizador.domain.mapper;

import com.arkahv.cotizador.domain.model.Cotizacion;
import com.arkahv.cotizador.domain.model.Producto;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.CotizacionEntity;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.ProductoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public Producto toDomain(ProductoEntity productoEntity) {

        Producto producto = new Producto();
        producto.setId(productoEntity.getId());
        producto.setNombre(productoEntity.getNombre());
        producto.setPrecio(productoEntity.getPrecio());
        producto.setCantidad(productoEntity.getCantidad());
        producto.setProducto(productoEntity.getProducto());
        return producto;


    }
}
