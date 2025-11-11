package com.proyecto.proyecto_pyme_backend.mapper;

import com.proyecto.proyecto_pyme_backend.dto.RegionDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionRowMapper implements RowMapper<RegionDto> {

    @Override
    public RegionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        RegionDto dto = new RegionDto();
        dto.setId_region(rs.getInt("id_region"));
        dto.setRegion(rs.getString("region"));
        return dto;
    }
}
