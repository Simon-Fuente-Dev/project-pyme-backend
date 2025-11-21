package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.TipoRedDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoRedRowMapper implements RowMapper<TipoRedDto> {
    @Override
    public TipoRedDto mapRow(ResultSet rs, int rownum) throws SQLException {
        TipoRedDto dto = new TipoRedDto();
        dto.setId_red(rs.getInt("id_red"));
        dto.setNom_red(rs.getString("nom_red"));
        return dto;
    }
}
