package com.arka.arkahv.domain.port.in;

import java.util.Optional;

public interface ManageTemplatesUseCase {

    /**
     * Obtiene el contenido de una plantilla específica.
     * @param templateName El nombre de la plantilla a obtener.
     * @return Un Optional que contiene el contenido de la plantilla si se encuentra, o vacío si no.
     */
    Optional<String> getTemplate(String templateName);
    void sendTemplatedEmail(String toEmail, String templateName, String subject);
}
