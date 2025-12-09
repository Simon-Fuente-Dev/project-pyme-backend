package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.PymeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PymeRowMapper implements RowMapper<PymeDto> {

    @Override
    public PymeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PymeDto dto = new PymeDto();
        dto.nombre_pyme = rs.getString("nombre_pyme");
        dto.descripcion_pyme = rs.getString("descripcion_pyme");
        return  dto;
    }
}
