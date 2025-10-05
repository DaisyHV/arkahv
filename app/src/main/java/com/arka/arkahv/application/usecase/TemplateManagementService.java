package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.port.in.ManageTemplatesUseCase;
import com.arka.arkahv.domain.port.out.EmailSenderPort;
import com.arka.arkahv.domain.port.out.TemplateRepository;

import java.util.Optional;

public class TemplateManagementService implements ManageTemplatesUseCase {
    private final TemplateRepository templateRepository;
    private final EmailSenderPort emailSenderPort;

    /**
     * Constructor que inyecta el repositorio de plantillas.
     * @param templateRepository El puerto de salida para acceder a las plantillas.
     */
    public TemplateManagementService(TemplateRepository templateRepository, EmailSenderPort emailSenderPort) {
        this.templateRepository = templateRepository;
        this.emailSenderPort = emailSenderPort;
    }

    /**
     * Obtiene el contenido de una plantilla a través del repositorio.
     * @param templateName El nombre de la plantilla.
     * @return Un Optional con el contenido de la plantilla.
     */
    @Override
    public Optional<String> getTemplate(String templateName) {
        return templateRepository.getTemplateContent(templateName);
    }

    @Override
    public void sendTemplatedEmail(String toEmail, String templateName, String subject) {
        Optional<String> templateContent = templateRepository.getTemplateContent(templateName);

        if (templateContent.isEmpty()) {
            // Lanza una excepción si la plantilla no se encuentra.
            // En una aplicación real, esto podría ser una excepción de negocio más específica.
            throw new RuntimeException("Plantilla no encontrada: " + templateName);
        }

        // Aquí podrías añadir lógica para reemplazar placeholders en la plantilla
        // Por ejemplo: templateContent.get().replace("{{userName}}", "Nombre del Usuario");
        // Para este ejemplo, enviamos el contenido tal cual.

        emailSenderPort.sendEmail(toEmail, subject, templateContent.get());
    }
}
