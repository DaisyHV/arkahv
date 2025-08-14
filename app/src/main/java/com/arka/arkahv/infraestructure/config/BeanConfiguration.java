package com.arka.arkahv.infraestructure.config;

import com.arka.arkahv.application.usecase.CategoryApplicationService;
import com.arka.arkahv.application.usecase.OrderApplicationService;
import com.arka.arkahv.application.usecase.ProductApplicationService;
import com.arka.arkahv.domain.port.in.CategoryUseCase;
import com.arka.arkahv.domain.port.in.OrderUseCase;
import com.arka.arkahv.domain.port.in.ProductUseCase;
import com.arka.arkahv.domain.port.out.CategoryRepositoryPort;
import com.arka.arkahv.domain.port.out.OrderRepositoryPort;
import com.arka.arkahv.domain.port.out.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}



