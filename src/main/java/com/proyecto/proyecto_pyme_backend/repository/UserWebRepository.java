package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.PymeUsuDto;
import com.proyecto.proyecto_pyme_backend.mapper.PymeUsuMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class UserWebRepository {

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
                WHERE usu.nom_usuario = :username
                    AND usu.id_tipo_usuario = 2;
                """;
        Map<String, Object> parametros = Map.of("username", username);
        return consultaGenerica.objetoUnico(sql, parametros, new PymeUsuMapper());

    }
}
