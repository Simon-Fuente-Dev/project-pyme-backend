package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.PymeUsuDto;
import com.proyecto.proyecto_pyme_backend.mapper.PymeUsuMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.HashMap;
import java.util.Map;


@Repository
public class UserRepository {

    @Autowired
    private ConsultaGenerica consultaGenerica;


    public PymeUsuDto obtenerUsuPyme(String username) {
        String sql = """
                SELECT usu.id_usuario,
                       usu.nom_usuario,
                       tp.nombre_pyme,
                       usu.password,
                       tp.id_pyme
                FROM tbl_usuario usu
                INNER JOIN tbl_usuario_pyme tup
                    ON usu.id_usuario = tup.id_usuario
                INNER JOIN tbl_pyme tp
                    ON tp.id_pyme = tup.id_pyme
                WHERE usu.nom_usuario = :username ;
               
                """;
        Map<String, Object> parametros = Map.of("username", username);
        return consultaGenerica.objetoUnico(sql, parametros, new PymeUsuMapper());
//        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
//        sqlParams.addValue("username", username);
//
//        try {
//            return namedParameterJdbcTemplate.queryForObject(sql, sqlParams, new PymeUsuMapper());
//
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }

    }
}
