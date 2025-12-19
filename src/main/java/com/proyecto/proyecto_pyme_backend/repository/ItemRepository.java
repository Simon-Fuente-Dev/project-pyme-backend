package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.ItemDto;
import com.proyecto.proyecto_pyme_backend.dto.ItemPymeDto;
import com.proyecto.proyecto_pyme_backend.mapper.ItemPymeRowMapper;
import com.proyecto.proyecto_pyme_backend.mapper.ItemRowMapper;
import com.proyecto.proyecto_pyme_backend.request.AddEditItemRequest;
import com.proyecto.proyecto_pyme_backend.request.DeleteItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    @Autowired
    private ConsultaGenerica consultaGenerica;

    public List<ItemDto> listarTipoItem() {
        String sql = """
                    SELECT id_tipo_item,\s
                           descripcion\s
                    FROM TBL_TIPO_ITEM;
                """;
        return consultaGenerica.listaResultados(sql, Map.of(), new ItemRowMapper());
    }

    public List<ItemPymeDto> obtenerItemPyme(Integer id_pyme) {
        String sql = """
                    SELECT ti.id_item,
                           ti.nombre,
                           ti.descripcion as "desc_item",
                           ti.precio,
                           ti.duracion_min,
                           ti.duracion_max,
                           tti.id_tipo_item,
                           tti.descripcion as "desc_tipo_item",
                           tss.id_sub_servicio,
                           tss.tipo_sub_servicio
                    FROM tbl_item ti
                    INNER JOIN tbl_tipo_item tti
                        ON ti.id_tipo_item = tti.id_tipo_item
                    INNER JOIN tbl_sub_servicio tss
                        ON ti.id_tipo_sub_servicio = tss.id_sub_servicio
                    INNER JOIN tbl_pyme tp
                        ON tp.id_pyme = ti.id_pyme
                    WHERE ti.id_pyme = :id_pyme
                    ORDER BY ti.id_item;
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id_pyme", id_pyme);
        return consultaGenerica.listaResultados(sql, parametros, new ItemPymeRowMapper());
    }

    public Integer agregarItemPyme(Integer id_pyme, AddEditItemRequest request) {

        String sql = """
                    INSERT INTO tbl_item(nombre, descripcion, precio, duracion_min, duracion_max, 
                                         id_pyme, id_tipo_item, id_tipo_sub_servicio)
                                  VALUES(:nombre, :descripcion, :precio, :duracion_min, :duracion_max, 
                                         :id_pyme, :id_tipo_item, :id_tipo_sub_servicio);
        """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", request.getNombre());
        parametros.put("descripcion", request.getDescripcion());
        parametros.put("precio", request.getPrecio());
        parametros.put("duracion_min", request.getDuracion_min());
        parametros.put("duracion_max", request.getDuracion_max());
        parametros.put("id_pyme", id_pyme);
        parametros.put("id_tipo_item", request.getTipoItem());
        parametros.put("id_tipo_sub_servicio", request.getTipoServicio());

        Integer filaAfectada = consultaGenerica.insertarYObtenerId(sql, parametros, "id_item");
        return filaAfectada;
    }

    public Integer editarItemPyme(Integer id_pyme, AddEditItemRequest request) {
        String sql = """
                UPDATE tbl_item SET nombre = :nombre, descripcion = :descripcion,precio = :precio, duracion_min = :duracion_min, duracion_max = :duracion_max,
                                     id_tipo_item = :id_tipo_item, id_tipo_sub_servicio = :id_tipo_sub_servicio
                WHERE id_item = :id_item;
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", request.getNombre());
        parametros.put("descripcion", request.getDescripcion());
        parametros.put("precio", request.getPrecio());
        parametros.put("duracion_min", request.getDuracion_min());
        parametros.put("duracion_max", request.getDuracion_max());
        parametros.put("id_tipo_item", request.getTipoItem());
        parametros.put("id_tipo_sub_servicio", request.getTipoServicio());
        parametros.put("id_item", request.getId());

        Integer filaAfectada = consultaGenerica.actualizarEliminar(sql, parametros);
        return filaAfectada;

    }

    public ResponseEntity<ApiResponse<Integer>> eliminarItemPyme(Integer id_pyme, DeleteItemRequest request) {
        try{
            String sql = """
                    DELETE FROM  tbl_item WHERE id_item = :id_item AND id_pyme = :id_pyme;
                """;
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id_item", request.getId_item());
            parametros.put("id_pyme", id_pyme);

            Integer filaAfectada = consultaGenerica.actualizarEliminar(sql, parametros);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Item eliminado con exito!", filaAfectada)
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Error al eliminar el item", 0)
            );
        }

//        return itemRepository.eliminarItemPyme(id_pyme, request);
    }


}
