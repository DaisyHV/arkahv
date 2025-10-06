package arka.gestor_solicitudes.infraestructure.adapter.out.persistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import arka.gestor_solicitudes.application.service.SolicitudCotizacionService;
import arka.gestor_solicitudes.application.service.SolicitudCotizacionServiceImpl;
import arka.gestor_solicitudes.domain.model.ProductoCotizado;
import arka.gestor_solicitudes.domain.model.SolicitudCotizacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class SolicitudCotizacionAdapter implements SolicitudCotizacionService {

    private final WebClient webClientCotizador;
    private final WebClient webClientArka;

    public SolicitudCotizacionAdapter(
            @Qualifier("webClientCotizador") WebClient webClientCotizador,
            @Qualifier("webClientArka") WebClient webClientArka
    ) {
        this.webClientCotizador = webClientCotizador;
        this.webClientArka = webClientArka;
    }

    @Override
    public Mono<SolicitudCotizacion> obtenerCotizacion(Integer cotizacionId) {
        return webClientCotizador.get()
                .uri("/cotizaciones/{id}", cotizacionId)
                .retrieve()
                .bodyToMono(SolicitudCotizacion.class)
                .map(cotizacion -> {
                    double total = cotizacion.getTotal();
                    double impuesto = this.calcularImpuesto(total, 0.19); // Ejemplo con tasa de impuesto del 19%
                    cotizacion.setImpuesto(impuesto);
                    cotizacion.setTotalFinal(total + impuesto);
                    return cotizacion;
                });
    }

    @Override
    public Mono<String> generarOrdenEfectuada(Integer cotizacionId, String token){
        System.out.println(token);
        return webClientCotizador.get()
                .uri("/cotizaciones/{id}", cotizacionId)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(SolicitudCotizacion.class)
                .map(cotizacion -> {
                    double total = cotizacion.getTotal();
                    double impuesto = this.calcularImpuesto(total, 0.19);
                    cotizacion.setImpuesto(impuesto);
                    cotizacion.setTotalFinal(total + impuesto);
                    return cotizacion;
                })
                .flatMap(cotizacion -> {
                    // Armar el map de la orden
                    Map<String, Object> orden = new HashMap<>();

                   // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                   // String fechaFormateada = cotizacion.getFecha_solicitud().format(formatter);
                    orden.put("date_order", "2024-06-20T12:00:00");

                    orden.put("total", cotizacion.getTotal());
                    orden.put("customer", 1);

                    List<Map<String, Object>> detalles = new ArrayList<>();
                    for (ProductoCotizado detalle : cotizacion.getProductos()) {
                        Map<String, Object> detalleMap = new HashMap<>();
                        Map<String, Object> productMap = new HashMap<>();
                        productMap.put("id", detalle.getProducto());
                        detalleMap.put("product", productMap);
                        detalleMap.put("numberProducts", detalle.getCantidad());
                        detalles.add(detalleMap);
                    }
                    orden.put("details", detalles);
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        String jsonOrden = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orden);
                        System.out.println(" JSON final de la orden:");
                        System.out.println(jsonOrden);
                    } catch (Exception e) {
                        System.out.println(" Error al convertir orden a JSON: " + e.getMessage());
                    }
                    // Hacer el POST y devolver el mensaje como Mono<String>
                    return webClientArka.post()
                            .uri("/arka/orders")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .header(HttpHeaders.COOKIE, "JSESSIONID=73D6E720BECD8E98E6BCDCD25ADDEC4F")
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .bodyValue(orden)
                            .retrieve()
                            .bodyToMono(Void.class)
                            .thenReturn("Orden creada con éxito")
                            .onErrorResume(err -> Mono.just("Error al crear orden: " + err.getMessage()));
    });}


    public double calcularImpuesto(double monto, double tasaImpuesto) {
        return monto * tasaImpuesto;
    }

}



