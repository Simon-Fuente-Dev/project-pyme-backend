package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.dto.ComunaDto;
import com.proyecto.proyecto_pyme_backend.mapper.ComunaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComunaRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ComunaDto> obtenerComunas(Integer id_region) {
        String sql = """
                SELECT id_comuna,
                       comuna
                FROM tbl_comuna
                WHERE id_region = :id_region;
                """;
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("id_region", id_region);

        try {

            return namedParameterJdbcTemplate.query(sql, sqlParams, new ComunaRowMapper());

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
