package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.ItemPymeDto;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemPymeRowMapper implements RowMapper<ItemPymeDto> {
    @Override
    public ItemPymeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemPymeDto dto = new ItemPymeDto();
        dto.setId_item(rs.getInt("id_item"));
        dto.setNombre(rs.getString("nombre"));
        dto.setDesc_item(rs.getString("desc_item"));
        dto.setPrecio(rs.getInt("precio"));
        dto.setDuracion_min(rs.getInt("duracion_min"));
        dto.setDuracion_max(rs.getInt("duracion_max"));
        dto.setId_tipo_item(rs.getInt("id_tipo_item"));
        dto.setDesc_tipo_item(rs.getString("desc_tipo_item"));
        dto.setId_sub_servicio(rs.getInt("id_sub_servicio"));
        dto.setTipo_sub_servicio(rs.getString("tipo_sub_servicio"));
        return dto;
    }
}
