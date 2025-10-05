package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.port.out.ImageStoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class S3ImageStorageAdapter implements ImageStoragePort {
    private final S3Client s3Client;

    // El nombre del bucket para imágenes se inyecta desde las propiedades de la aplicación
    @Value("${aws.s3.images-bucket-name}")
    private String bucketName;

    /**
     * Constructor que inicializa el cliente S3.
     * La región de AWS se inyecta desde las propiedades de la aplicación.
     * @param awsRegion La región de AWS.
     */
    public S3ImageStorageAdapter(@Value("${aws.region}") String awsRegion) {
        this.s3Client = S3Client.builder()
                .region(Region.of(awsRegion)) // Configura la región del cliente S3
                .build();
    }

    /**
     * Implementación del método para subir una imagen a S3.
     * Genera una clave única para el objeto en S3.
     * @param imageStream El flujo de entrada de la imagen.
     * @param originalFileName El nombre original del archivo.
     * @param contentType El tipo MIME de la imagen.
     * @return La URL pública de la imagen subida en S3.
     */
    @Override
    public String uploadImage(InputStream imageStream, String originalFileName, String contentType) {
        try {
            String key = generateKey(originalFileName); // Genera una clave única para el objeto S3


            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(contentType)
                    .build();

            // Sube el objeto a S3 desde el InputStream
            s3Client.putObject(putRequest, RequestBody.fromInputStream(imageStream, imageStream.available()));

            // Obtiene la URL pública del objeto subido
            return s3Client
                    .utilities()
                    .getUrl(GetUrlRequest.builder()
                            .bucket(bucketName).key(key).build())
                    .toString();
        } catch (IOException e) {
            // Error al leer el InputStream
            System.err.println("Error de I/O al subir la imagen a S3: " + e.getMessage());
            throw new RuntimeException("¡Ups! Hubo un problema al leer la imagen para subirla a S3", e);
        } catch (S3Exception e) {
            // Errores específicos de S3 (ej. permisos, bucket no existe)
            System.err.println("Error de S3 al subir la imagen: " + e.awsErrorDetails().errorMessage());
            throw new RuntimeException("¡Ups! Hubo un problema con S3 al subir la imagen", e);
        } catch (Exception e) {
            // Cualquier otro error inesperado
            System.err.println("Error inesperado al subir la imagen a S3: " + e.getMessage());
            throw new RuntimeException("¡Ups! Hubo un problema inesperado al subir la imagen a S3", e);
        }
    }

    /**
     * Genera una clave única para el objeto S3 combinando un UUID con el nombre original del archivo.
     * Esto ayuda a evitar colisiones de nombres.
     * @param originalFilename El nombre original del archivo.
     * @return La clave única para S3.
     */
    private String generateKey(String originalFilename) {
        // Asegura que el nombre de archivo no sea nulo para evitar NullPointerException
        String safeOriginalFilename = (originalFilename != null) ? originalFilename : "untitled";
        return UUID.randomUUID().toString() + "-" + safeOriginalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }


}
