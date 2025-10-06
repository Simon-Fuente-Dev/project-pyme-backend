package com.proyecto.proyecto_pyme_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private Number id_usuario;
    private Number id_pyme;
    private String nombre_pyme;
}
