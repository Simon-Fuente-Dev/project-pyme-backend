package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.PymeDto;
import com.proyecto.proyecto_pyme_backend.mapper.PymeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PymeRepository {

    @Autowired
    private ConsultaGenerica consultaGenerica;
    public PymeDto obtenerDataPyme(Integer id_pyme) {
        String sql = """
                SELECT nombre_pyme,
                       descripcion_pyme
                FROM tbl_pyme
                WHERE id_pyme=:id_pyme
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id_pyme", id_pyme);
        return consultaGenerica.objetoUnico(sql, parametros, new PymeRowMapper());
    }

    public ResponseEntity<ApiResponse<Integer>> modificarNomPyme(Integer id_pyme, String nombre_pyme) {
        System.out.println(nombre_pyme);
        System.out.println(id_pyme);
        String sql = """
                UPDATE tbl_pyme
                SET  nombre_pyme = :nombre_pyme
                WHERE id_pyme= :id_pyme 
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre_pyme", nombre_pyme);
        parametros.put("id_pyme", id_pyme);

        Integer filaAfectada = consultaGenerica.actualizarEliminar(sql, parametros);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Nombre modificado con exito! ", filaAfectada)
        );
    }

    public ResponseEntity<ApiResponse<Integer>> modificarDescPyme(Integer id_pyme, String descripcion_pyme) {
        String sql = """
                UPDATE tbl_pyme
                SET  descripcion_pyme = :descripcion_pyme
                WHERE id_pyme=:id_pyme 
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("descripcion_pyme", descripcion_pyme);
        parametros.put("id_pyme", id_pyme);

        Integer filaAfectada = consultaGenerica.actualizarEliminar(sql, parametros);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Descripcion modificado con exito! ", filaAfectada)
        );
    }
}
