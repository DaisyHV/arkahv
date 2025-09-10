package com.arkahv.cotizador.application.service;

import com.arkahv.cotizador.domain.mapper.CotizacionMapper;
import com.arkahv.cotizador.domain.mapper.ProductMapper;
import com.arkahv.cotizador.domain.model.Cotizacion;
import com.arkahv.cotizador.domain.model.Producto;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.CotizacionEntity;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.entity.ProductoEntity;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.repository.CotizacionRepository;
import com.arkahv.cotizador.infraestructure.adapter.out.persistence.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CotizacionServiceImpl implements CotizacionService{

    private final CotizacionRepository cotizacionRepository;
    private final CotizacionMapper cotizacionMapper;
    private final ProductoRepository productoRepo;
    private final ProductMapper productoMapper;

    @Override
    public Mono<Cotizacion> generarCotizacion(Cotizacion cotizacion) {
        CotizacionEntity cotizacionEntity = new CotizacionEntity();
        cotizacionEntity.setId(cotizacion.getId());
        cotizacionEntity.setCliente(cotizacion.getCliente());
        cotizacionEntity.setFecha_solicitud(cotizacion.getFecha_solicitud());
        cotizacionEntity.setTotal(cotizacion.getTotal());
        cotizacionEntity.setEstado(cotizacion.getEstado());

        return cotizacionRepository.save(cotizacionEntity) // guarda la cotización primero
                .flatMap(saved -> {
                    List<ProductoEntity> productosEntity = cotizacion.getProductos().stream()
                            .map(item -> {

                                ProductoEntity productoEntity = new ProductoEntity();
                                productoEntity.setId(item.getId());
                                productoEntity.setIdCotizacion(saved.getId());
                                productoEntity.setNombre(item.getNombre());
                                productoEntity.setCantidad(item.getCantidad());
                                productoEntity.setPrecio(item.getPrecio());
                                productoEntity.setProducto(item.getProducto());
                                return productoEntity;

                            })
                            .collect(Collectors.toList());

                    return productoRepo.saveAll(productosEntity) // guarda los ítems
                            .collectList() // convierte Flux<QuotationItem> a Mono<List<QuotationItem>>
                            .map(savedItems -> {
                                List<Producto> productosDominio = savedItems.stream()
                                        .map(productoMapper::toDomain)
                                        .collect(Collectors.toList());;

                                Cotizacion cotizacionDominio = cotizacionMapper.toDomain(saved, savedItems);

                                // Seteamos la lista de productos dentro de la cotización dominio
                                //cotizacionDominio.setProductos(productosDominio);

                                return cotizacionDominio;
                            });
                });

        //hacer mapeo de Cotizacion a CotizacionEntity mediante mapeo manual
    }

    public Mono<CotizacionEntity> obtenerPorId(Integer id) {
        return cotizacionRepository.findById(id);
    }

    public Flux<CotizacionEntity> listarCotizaciones() {
        return cotizacionRepository.findAll();
    }





    @Override
    public Mono<Cotizacion> obtenerCotizacion(Integer cotizacionId) {
        //Cotizacion cotizacion = new Cotizacion();
        Mono<CotizacionEntity> cotizacionEntity = cotizacionRepository.findById(cotizacionId);
        Flux<ProductoEntity> productosEntity = productoRepo.findByIdCotizacion(cotizacionId);

        return cotizacionEntity.flatMap(ce ->
                productosEntity.collectList().map(peList -> {
                    return cotizacionMapper.toDomain(ce, peList);
                })
        );
    }
}
