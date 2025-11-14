package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.SubServicioDto;
import com.proyecto.proyecto_pyme_backend.mapper.SubServicioRowMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SubServicioRepository {

    @Autowired
    private ConsultaGenerica consultaGenerica;

    public List<SubServicioDto> listarSubServicio(Integer id_servicio) {
        String sql = """
                SELECT id_sub_servicio,
                       tipo_sub_servicio
                FROM tbl_sub_servicio
                WHERE id_tipo_servicio = :id_tipo_servicio;
                """;
        Map<String, Object> parametros = Map.of("id_tipo_servicio", id_servicio);
        return consultaGenerica.listaResultados(sql, parametros, new SubServicioRowMapper());
    }
}
