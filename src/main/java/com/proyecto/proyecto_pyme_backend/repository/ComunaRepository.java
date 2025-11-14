package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.ComunaDto;
import com.proyecto.proyecto_pyme_backend.mapper.ComunaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ComunaRepository {
    @Autowired
    private ConsultaGenerica consultaGenerica;

    public List<ComunaDto> obtenerComunas(Integer id_region) {
        String sql = """
                SELECT id_comuna,
                       comuna
                FROM tbl_comuna
                WHERE id_region = :id_region;
                """;
        Map<String, Object> params = Map.of("id_region", id_region);
        return consultaGenerica.listaResultados(sql, params, new ComunaRowMapper());
    }
}
