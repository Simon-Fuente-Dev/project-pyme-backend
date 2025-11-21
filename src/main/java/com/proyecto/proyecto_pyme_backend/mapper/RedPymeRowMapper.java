package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.RedPymeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RedPymeRowMapper implements RowMapper<RedPymeDto> {
    @Override
    public RedPymeDto mapRow(ResultSet rs, int rownum) throws SQLException {
        RedPymeDto dto = new RedPymeDto();
        dto.setId_tipo_red(rs.getInt("id_tipo_red"));
        dto.setId_red_pyme(rs.getInt("id_red_pyme"));
        dto.setUrl(rs.getString("url"));
        dto.setNumero_telefono(rs.getInt("numero_telefono"));
        return dto;
    }
}
