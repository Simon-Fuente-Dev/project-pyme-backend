package com.proyecto.proyecto_pyme_backend.repository;
import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.TipoServDto;
import com.proyecto.proyecto_pyme_backend.mapper.TipoServRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TipoServRepository {

    @Autowired
    private ConsultaGenerica consultaGenerica;


    public List<TipoServDto> listarTipoServ() {
        String sql = """
                select id_tipo_servicio,
                       tipo_servicio
                from tbl_tipo_servicio;
                """;
        return consultaGenerica.listaResultados(sql, Map.of(), new TipoServRowMapper());
    }

    public List<TipoServDto> listarServPyme(Integer id_pyme) {
        String sql = """
                SELECT tts.tipo_servicio,
                       ttsp.id_tipo_servicio
                FROM tbl_tipo_servicio tts
                INNER JOIN tbl_tipo_serv_pyme ttsp
                    ON tts.id_tipo_servicio = ttsp.id_tipo_servicio
                INNER JOIN tbl_pyme tp
                    ON ttsp.id_pyme = tp.id_pyme
                WHERE tp.id_pyme = :id_pyme;
                """;
        Map<String, Object> params = Map.of("id_pyme", id_pyme);

        return consultaGenerica.listaResultados(sql, params, new TipoServRowMapper());
    }
}
