package com.proyecto.proyecto_pyme_backend.request;

import lombok.Data;

@Data
public class RegisterUsuRequest {
    public String pnombre;
    public String snombre;
    public String appaterno;
    public String apmaterno;
    public String nomUsuario;
    public String email;
    public String password;
    public String nomPyme;
    public String descPyme;
    public Integer tipoServicio;
    public Integer[] subServicio;
    public Integer region;
    public Integer comuna;
    public String direccion;
}
