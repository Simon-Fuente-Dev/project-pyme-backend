package com.proyecto.proyecto_pyme_backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {

    private static CustomUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        throw new IllegalStateException("No se encontró un usuario autenticado.");
    }

    public static Integer getIdUsuario() {
        return getUserDetails().getIdUsuario();
    }

    public static Integer getIdPyme() {
        return getUserDetails().getIdPyme();
    }

    public static String getUsername() {
        return getUserDetails().getUsername();
    }
}
