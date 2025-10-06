package arka.gestor_solicitudes.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClientCotizador() {
        return WebClient.builder()
                .baseUrl("http://3.94.84.118:80")  // Aquí va la URL del Microservicio Cotizador
                .build();
    }

    @Bean
    public WebClient webClientArka() {
        return WebClient.builder()
                .baseUrl("http://3.89.55.92:80")  // Aquí va la URL del Microservicio ARKA
                .build();
    }
}
