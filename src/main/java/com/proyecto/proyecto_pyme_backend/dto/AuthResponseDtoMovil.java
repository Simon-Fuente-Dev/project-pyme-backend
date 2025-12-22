package com.proyecto.proyecto_pyme_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDtoMovil {
    private String token;
    private Number id_usuario;
}
