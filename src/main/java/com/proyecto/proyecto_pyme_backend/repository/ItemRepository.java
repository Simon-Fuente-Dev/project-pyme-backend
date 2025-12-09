package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.ItemDto;
import com.proyecto.proyecto_pyme_backend.mapper.ItemRowMapper;
import com.proyecto.proyecto_pyme_backend.request.AddEditItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public Integer agregarItemPyme(Integer id_pyme, AddEditItemRequest request) {

    }
}
