package com.proyecto.proyecto_pyme_backend.Utils;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Component
public class ConsultaGenerica {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ConsultaGenerica(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🔹 1. Consulta que devuelve lista de objetos
    public <T> List<T> listaResultados(String sql, Map<String, Object> parametros, RowMapper<T> rowMapper) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource(parametros);
            return jdbcTemplate.query(sql, paramSource, rowMapper);
        } catch (Exception e) {
            System.err.println("❌ Error ejecutando listaResultados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // 🔹 2. Consulta que devuelve un único valor (por tipo)
    public <T> T valorUnico(String sql, Map<String, Object> parametros, Class<T> tipo) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource(parametros);
            return jdbcTemplate.queryForObject(sql, paramSource, tipo);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            System.err.println("❌ Error ejecutando valorUnico: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // 🔹 3. Consulta que devuelve un solo objeto mapeado
    public <T> T objetoUnico(String sql, Map<String, Object> parametros, RowMapper<T> rowMapper) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource(parametros);
            return jdbcTemplate.queryForObject(sql, paramSource, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            System.err.println("❌ Error ejecutando objetoUnico: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // 🔹 4. Ejecutar INSERT y obtener ID autogenerado
    public Integer insertarYObtenerId(String sql, Map<String, Object> parametros, String nombreColumnaId) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource(parametros);
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(sql, paramSource, keyHolder, new String[]{nombreColumnaId});

            Number idGenerado = keyHolder.getKey();
            return (idGenerado != null) ? idGenerado.intValue() : null;

        } catch (Exception e) {
            System.err.println("❌ Error ejecutando insertarYObtenerId: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // 🔹 5. Ejecutar UPDATE / DELETE y devolver filas afectadas
    public int actualizarEliminar(String sql, Map<String, Object> parametros) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource(parametros);
            return jdbcTemplate.update(sql, paramSource);
        } catch (Exception e) {
            System.err.println("❌ Error ejecutando actualizar: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
