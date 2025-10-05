package com.arka.arkahv.domain.port.out;

import java.io.InputStream;

public interface ImageStoragePort {
    /**
     * Sube una imagen a un sistema de almacenamiento.
     *
     * @param imageStream El flujo de entrada de la imagen.
     * @param fileName El nombre original del archivo de la imagen.
     * @param contentType El tipo de contenido (MIME type) de la imagen (ej. "image/jpeg").
     * @return La URL pública o identificador de la imagen almacenada.
     */
    String uploadImage(InputStream imageStream, String fileName, String contentType);
}
