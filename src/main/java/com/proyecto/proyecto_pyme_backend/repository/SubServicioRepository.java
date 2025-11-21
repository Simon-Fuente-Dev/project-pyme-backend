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

    public List<SubServicioDto> listarSubServPyme(Integer id_pyme) {
        String sql = """
                SELECT tss.tipo_sub_servicio,
                       tssp.id_sub_servicio
                FROM tbl_sub_servicio tss
                INNER JOIN tbl_sub_serv_pyme tssp
                    ON tss.id_sub_servicio = tssp.id_sub_servicio
                INNER JOIN tbl_pyme tp
                    ON tssp.id_pyme = tp.id_pyme
                WHERE tp.id_pyme = :id_pyme;
                """;
        Map<String, Object> parametros = Map.of("id_pyme", id_pyme);
        return consultaGenerica.listaResultados(sql, parametros, new SubServicioRowMapper());
    }
}
