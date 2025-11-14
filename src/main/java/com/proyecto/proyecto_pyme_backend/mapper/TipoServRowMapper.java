package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.TipoServDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoServRowMapper implements RowMapper<TipoServDto> {
    @Override
    public TipoServDto mapRow(ResultSet rs, int rownum) throws SQLException {
        TipoServDto dto = new TipoServDto();
        dto.setId_tipo_servicio(rs.getInt("id_tipo_servicio"));
        dto.setTipo_servicio(rs.getString("tipo_servicio"));
        return  dto;
    }
}
