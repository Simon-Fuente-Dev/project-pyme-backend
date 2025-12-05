package com.proyecto.proyecto_pyme_backend.dto;

import lombok.Data;

@Data
public class RedPymeDto {
    private Integer id_tipo_red;
    private String nom_red;
    private Integer id_red_pyme;
    private String url;
    private Integer numero_telefono;
}
