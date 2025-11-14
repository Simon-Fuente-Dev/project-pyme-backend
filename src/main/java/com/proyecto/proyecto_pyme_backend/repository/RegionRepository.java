package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.RegionDto;
import com.proyecto.proyecto_pyme_backend.mapper.RegionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RegionRepository {

    @Autowired
    private ConsultaGenerica consultaGenerica;

    public List<RegionDto> obtenerRegiones() {
        String sql = "SELECT id_region,region FROM tbl_region;";

        return consultaGenerica.listaResultados(sql, Map.of(), new RegionRowMapper() );
    }
}
