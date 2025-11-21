package com.proyecto.proyecto_pyme_backend.Utils;

import org.springframework.stereotype.Component;
import org.apache.commons.validator.routines.UrlValidator;

@Component
public class Validaciones {
    public boolean esUrlValida(String url) {
        UrlValidator validator = new UrlValidator(
                new String[]{"http", "https"} // solo protocolos válidos
        );
        return validator.isValid(url);
    }

}
