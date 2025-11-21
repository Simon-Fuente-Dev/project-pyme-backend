package com.proyecto.proyecto_pyme_backend.request;

import lombok.Data;

@Data
public class AgregarRedRequest {
    public Integer idRed;
    public String url;
    public Integer numeroTelefono;
}
