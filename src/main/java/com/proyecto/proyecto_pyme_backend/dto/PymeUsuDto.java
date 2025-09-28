package com.proyecto.proyecto_pyme_backend.dto;

import lombok.Data;

@Data
public class PymeUsuDto {
    private Number id_usuario;
    private String nom_usuario;
    private String password;
    private String nombre_pyme;
    private Number id_pyme;
}
