package com.proyecto.proyecto_pyme_backend.request;

import lombok.Data;

@Data
public class UsuAuthRequest {
    private String username;
    private String password;
}
