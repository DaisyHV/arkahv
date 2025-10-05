package com.arka.arkahv.infraestructure.config;

import com.arka.arkahv.application.usecase.ProductImageUploadService;
import com.arka.arkahv.application.usecase.TemplateManagementService;
import com.arka.arkahv.domain.port.in.ManageTemplatesUseCase;
import com.arka.arkahv.domain.port.in.UploadProductImageUseCase;
import com.arka.arkahv.domain.port.out.EmailSenderPort;
import com.arka.arkahv.domain.port.out.ImageStoragePort;
import com.arka.arkahv.domain.port.out.TemplateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AppConfig {
    /**
     * Define el bean para el caso de uso ManageTemplatesUseCase.
     * Spring inyectará automáticamente la implementación de TemplateRepository (S3TemplateAdapter)
     * ya que está marcada con @Component.
     * @param templateRepository El adaptador de infraestructura para el repositorio de plantillas.
     * @return Una instancia de TemplateManagementService que implementa el caso de uso.
     */
    @Bean
    public ManageTemplatesUseCase manageTemplatesUseCase(TemplateRepository templateRepository, EmailSenderPort emailSenderPort) {
        return new TemplateManagementService(templateRepository,emailSenderPort);
    }
    @Bean
    public S3Client s3Client(@Value("${aws.accessKey}") String accessKey,
                             @Value("${aws.secretKey}") String secretKey,
                             @Value("${aws.region}") String region) {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .build();
    }
    @Bean
    public UploadProductImageUseCase uploadProductImageUseCase(ImageStoragePort imageStoragePort) {
        return new ProductImageUploadService(imageStoragePort);
    }
}
