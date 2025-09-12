package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.dto.RegionDto;
import com.proyecto.proyecto_pyme_backend.mapper.RegionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.synth.Region;
import java.util.List;

@Repository
public class RegionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RegionDto> obtenerRegiones() {
        String sql = "SELECT id_region,region FROM tbl_region;";
        return jdbcTemplate.query(sql, new RegionRowMapper());
    }
}
