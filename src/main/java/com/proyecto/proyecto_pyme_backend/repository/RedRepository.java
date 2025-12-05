package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.Utils.Validaciones;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.RedPymeDto;
import com.proyecto.proyecto_pyme_backend.dto.TipoRedDto;
import com.proyecto.proyecto_pyme_backend.mapper.RedPymeRowMapper;
import com.proyecto.proyecto_pyme_backend.mapper.TipoRedRowMapper;
import com.proyecto.proyecto_pyme_backend.request.AgregarRedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

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

    public ResponseEntity<ApiResponse<Integer>> agregarRedPyme(AgregarRedRequest request, Integer id_pyme) {
        if(request.getUrl() != "" && !validaciones.esUrlValida(request.getUrl())) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Url no es segura", 0)
            );
        }
        String sql = """
                INSERT INTO tbl_red_pyme(id_pyme, id_tipo_red, url, numero_telefono)\s
                            VALUES(:id_pyme, :id_tipo_red, :url, :numero_telefono)\s
                """;
        Map<String, Object> parametros = Map.of(
                "id_pyme", id_pyme,
                "id_tipo_red", request.getIdRed(),
                "url", request.getUrl(),
                "numero_telefono", request.getNumeroTelefono()
        );
        Integer id_red_pyme = consultaGenerica.insertarYObtenerId(sql, parametros,"id_red_pyme");

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Red social agregada con exito! ", id_red_pyme)
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
