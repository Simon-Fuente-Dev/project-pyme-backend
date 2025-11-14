package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.SubServicioDto;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubServicioRowMapper implements RowMapper<SubServicioDto> {
    @Override
    public SubServicioDto mapRow(ResultSet rs, int rownum) throws SQLException {
        SubServicioDto dto = new SubServicioDto();
        dto.setId_sub_servicio(rs.getInt("id_sub_servicio"));
        dto.setTipo_sub_servicio(rs.getString("tipo_sub_servicio"));
        return  dto;
    }
}
