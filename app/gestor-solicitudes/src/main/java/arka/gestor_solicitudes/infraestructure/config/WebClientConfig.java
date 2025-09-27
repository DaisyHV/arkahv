package arka.gestor_solicitudes.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClientCotizador() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082")  // Aquí va la URL del Microservicio B
                .build();
    }

    @Bean
    public WebClient webClientArka() {
        return WebClient.builder()
                .baseUrl("http://localhost:8089")  // Aquí va la URL del Microservicio B
                .build();
    }
}
