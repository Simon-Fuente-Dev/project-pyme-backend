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
}
