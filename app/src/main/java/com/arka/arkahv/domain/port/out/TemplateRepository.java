package com.arka.arkahv.domain.port.out;

import java.util.Optional;

public interface TemplateRepository {

    Optional<String> getTemplateContent(String templateName);
}
