package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.Utils.Validaciones;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.RedPymeDto;
import com.proyecto.proyecto_pyme_backend.dto.TipoRedDto;
import com.proyecto.proyecto_pyme_backend.mapper.RedPymeRowMapper;
import com.proyecto.proyecto_pyme_backend.mapper.TipoRedRowMapper;
import com.proyecto.proyecto_pyme_backend.request.AgregarEditarRedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RedRepository {
    @Autowired
    private ConsultaGenerica consultaGenerica;

    @Autowired
    private Validaciones validaciones;

    public List<TipoRedDto> listarTipoRed(Integer id_pyme) {
        String sql = """
                SELECT ttrs.id_tipo_red AS id_red,
                       ttrs.nom_red_social AS nom_red
                FROM tbl_tipo_red_social ttrs
                LEFT JOIN  tbl_red_pyme trp
                    on ttrs.id_tipo_red = trp.id_tipo_red AND trp.id_pyme = :id_pyme
                WHERE trp.id_tipo_red IS NULL;
                """;
        Map<String, Object> parametros = Map.of("id_pyme", id_pyme);
        return consultaGenerica.listaResultados(sql, parametros, new TipoRedRowMapper());
    }

    public ResponseEntity<ApiResponse<Integer>> agregarRedPyme(AgregarEditarRedRequest request, Integer id_pyme) {
        if(request.getUrl() != "" && !validaciones.esUrlValida(request.getUrl())) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Url no es segura", 0)
            );
        }
        String sql = """
                INSERT INTO tbl_red_pyme(id_pyme, id_tipo_red, url, numero_telefono)\s
                            VALUES(:id_pyme, :id_tipo_red, :url, :numero_telefono)\s
                """;
//        Map<String, Object> parametros = Map.of(
//                "id_pyme", id_pyme,
//                "id_tipo_red", request.getIdRed(),
//                "url", request.getUrl(),
//                "numero_telefono", request.getNumeroTelefono()
//        );

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("url", request.getUrl());
        parametros.put("numero_telefono", request.getNumeroTelefono());
        parametros.put("id_pyme", id_pyme);
        parametros.put("id_tipo_red", request.getIdRed());


        Integer id_red_pyme = consultaGenerica.insertarYObtenerId(sql, parametros,"id_red_pyme");

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Red social agregada con exito! ", id_red_pyme)
        );
    }

    public ResponseEntity<ApiResponse<Integer>> modificarRedPyme(AgregarEditarRedRequest request, Integer id_pyme) {
        if(request.getUrl() != "" && !validaciones.esUrlValida(request.getUrl())) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Url no es segura", 0)
            );
        }
        String sql = """
                UPDATE tbl_red_pyme SET url=:url, numero_telefono=:numero_telefono 
                WHERE id_pyme=:id_pyme AND  id_red_pyme=:id_red_pyme
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("url", request.getUrl());
        parametros.put("numero_telefono", request.getNumeroTelefono());
        parametros.put("id_pyme", id_pyme);
        parametros.put("id_red_pyme", request.getIdRedPyme());

        Integer filaAfectada = consultaGenerica.actualizarEliminar(sql, parametros);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Red social modificada con exito! ", filaAfectada)
        );
    }

    public ResponseEntity<ApiResponse<Integer>>  eliminarRedPyme(Integer id_red,Integer id_pyme){
        String sql = """
                DELETE FROM tbl_red_pyme
                WHERE id_pyme=:id_pyme AND  id_red_pyme=:id_red_pyme
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id_pyme", id_pyme);
        parametros.put("id_red_pyme", id_red);

        Integer filaAfectada = consultaGenerica.actualizarEliminar(sql, parametros);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Red social eliminada con exito! ", filaAfectada)
        );
    }

    public List<RedPymeDto> obtenerRedesPyme(Integer id_pyme) {
        String sql = """
                SELECT trp.id_tipo_red,
                       trp.id_red_pyme,
                       trp.url,
                       trp.numero_telefono,
                       ttrs.nom_red_social
                FROM tbl_red_pyme trp
                INNER JOIN tbl_pyme tp
                    ON tp.id_pyme = trp.id_pyme
                INNER JOIN tbl_tipo_red_social ttrs
                    ON trp.id_tipo_red = ttrs.id_tipo_red
                WHERE tp.id_pyme = :id_pyme;
                """;
        Map<String, Object> parametros = Map.of("id_pyme", id_pyme);
        return consultaGenerica.listaResultados(sql, parametros, new RedPymeRowMapper());
    }
}
