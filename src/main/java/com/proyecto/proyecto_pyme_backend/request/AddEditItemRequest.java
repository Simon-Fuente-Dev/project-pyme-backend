package com.proyecto.proyecto_pyme_backend.request;

import lombok.Data;

@Data
public class AddEditItemRequest {
    public Integer id;
    public String nombre;
    public String descripcion;
    public Integer tipoItem;
    public Integer tipoServicio;
    public Integer precio;
    public Integer duracion_min;
    public Integer duracion_max;
}
