package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.port.in.UploadProductImageUseCase;
import com.arka.arkahv.domain.port.out.ImageStoragePort;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ProductImageUploadService implements UploadProductImageUseCase {
    private final ImageStoragePort imageStoragePort;

    /**
     * Constructor que inyecta el puerto de almacenamiento de imágenes.
     * @param imageStoragePort El puerto de salida para almacenar imágenes.
     */
    public ProductImageUploadService(ImageStoragePort imageStoragePort) {
        this.imageStoragePort = imageStoragePort;
    }

    /**
     * Sube la imagen de un producto.
     * Extrae el InputStream, nombre y tipo de contenido del MultipartFile
     * y los pasa al puerto de almacenamiento.
     * @param image El archivo MultipartFile que contiene la imagen.
     * @return La URL pública de la imagen subida.
     * @throws RuntimeException Si ocurre un error de I/O al obtener el InputStream de la imagen.
     */
    @Override
    public String uploadProductImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("La imagen no puede ser nula o vacía.");
        }
        try {
            // El MultipartFile es de la capa de aplicación, aquí lo "desempaquetamos"
            // para pasar datos más agnósticos al puerto de almacenamiento.
            return imageStoragePort.uploadImage(image.getInputStream(), image.getOriginalFilename(), image.getContentType());
        } catch (IOException e) {
            System.err.println("Error al obtener el InputStream de la imagen: " + e.getMessage());
            throw new RuntimeException("Error al procesar la imagen para la carga", e);
        }
    }
}
