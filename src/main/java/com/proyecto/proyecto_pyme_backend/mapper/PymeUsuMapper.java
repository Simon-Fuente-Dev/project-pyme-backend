package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.PymeUsuDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PymeUsuMapper implements RowMapper<PymeUsuDto> {

    @Override
    public PymeUsuDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PymeUsuDto pymeUsuDto = new PymeUsuDto();
        pymeUsuDto.setId_usuario(rs.getInt("id_usuario"));
        pymeUsuDto.setNom_usuario(rs.getString("nom_usuario"));
        pymeUsuDto.setNombre_pyme(rs.getString("nombre_pyme"));
        pymeUsuDto.setPassword(rs.getString("password"));
        pymeUsuDto.setId_pyme(rs.getInt("id_pyme"));

        return pymeUsuDto;
    }
}
