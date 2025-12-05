package com.proyecto.proyecto_pyme_backend.request;

import lombok.Data;

@Data
public class AgregarEditarRedRequest {
    public Integer idRed;
    public Integer idRedPyme;
    public String url;
    public Integer numeroTelefono;
}
