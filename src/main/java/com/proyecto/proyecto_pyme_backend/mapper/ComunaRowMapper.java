package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.ComunaDto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComunaRowMapper implements RowMapper<ComunaDto>
{
    @Override
    public ComunaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ComunaDto dto = new ComunaDto();
        dto.setId_comuna(rs.getInt("id_comuna"));
        dto.setComuna(rs.getString("comuna"));
        return dto;
    }
}
