package com.proyecto.proyecto_pyme_backend.dto;

import lombok.Data;

@Data
public class ItemPymeDto {
    public Integer id_item;
    public String nombre;
    public String desc_item;
    public Integer precio;
    public Integer duracion_min;
    public Integer duracion_max;
    public Integer id_tipo_item;
    public String desc_tipo_item;
    public Integer id_sub_servicio;
    public String tipo_sub_servicio;
}
