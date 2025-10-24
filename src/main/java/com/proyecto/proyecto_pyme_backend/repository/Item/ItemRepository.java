package com.proyecto.proyecto_pyme_backend.repository.Item;

import com.proyecto.proyecto_pyme_backend.dto.Item.ItemDto;
import com.proyecto.proyecto_pyme_backend.mapper.Item.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ItemDto> listarTipoItem() {
        String sql = """
                    SELECT id_tipo_item,\s
                           descripcion\s
                    FROM TBL_TIPO_ITEM;
                """;
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }
}
