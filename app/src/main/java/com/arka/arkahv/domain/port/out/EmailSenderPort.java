package com.arka.arkahv.domain.port.out;

public interface EmailSenderPort {
    /**
     * Envía un correo electrónico.
     *
     * @param to El destinatario del correo.
     * @param subject El asunto del correo.
     * @param body El cuerpo del correo (puede ser HTML).
     */
    void sendEmail(String to, String subject, String body);
}
