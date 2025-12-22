package com.proyecto.proyecto_pyme_backend.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    public Integer id_usuario;
    public String nom_usuario;
    public String password;
}
