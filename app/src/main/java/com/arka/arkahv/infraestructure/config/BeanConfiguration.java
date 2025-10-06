package com.arka.arkahv.infraestructure.config;

import com.arka.arkahv.application.usecase.*;
import com.arka.arkahv.domain.port.in.*;
import com.arka.arkahv.domain.port.out.*;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.ComentarioRepositorio;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bouncycastle.util.io.pem.PemObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bouncycastle.util.io.pem.PemReader;

import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.security.KeyStore;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
@Configuration
public class BeanConfiguration {
    @Bean
    public ProductUseCase productUseCase(ProductRepositoryPort productRepository) {
        return new ProductApplicationService(productRepository);
    }
    @Bean
    public CategoryUseCase categoryUseCase(CategoryRepositoryPort categoryRepository) {
        return new CategoryApplicationService(categoryRepository);
    }
    @Bean
    public OrderUseCase orderUseCase(OrderRepositoryPort orderRepository) {
        return new OrderApplicationService(orderRepository);
    }
    @Bean
    public UserUseCase UserUseCase(UserRepositoryPort userRepository) {
        return new UserApplicationService(userRepository);
    }
    @Bean
    public ComentarioUseCase comentarioUseCase(ComentarioRepositoryPort comentarioRepository) {
        return new ComentarioApplicationService(comentarioRepository);
    }


}



