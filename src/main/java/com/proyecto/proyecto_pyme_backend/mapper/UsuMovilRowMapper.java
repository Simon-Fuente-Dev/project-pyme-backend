package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.UsuarioDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuMovilRowMapper implements RowMapper<UsuarioDto> {
    @Override
    public UsuarioDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioDto dto = new UsuarioDto();
        dto.setId_usuario(rs.getInt("id_usuario"));
        dto.setNom_usuario(rs.getString("nom_usuario"));
        dto.setPassword(rs.getString("password"));

        return dto;
    }
}
