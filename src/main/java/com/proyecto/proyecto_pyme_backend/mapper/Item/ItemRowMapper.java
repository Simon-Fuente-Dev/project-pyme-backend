package com.proyecto.proyecto_pyme_backend.mapper.Item;

import com.proyecto.proyecto_pyme_backend.dto.Item.ItemDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<ItemDto> {

    @Override
    public ItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemDto item = new ItemDto();
        item.setIdTipoItem(rs.getInt("id_tipo_item"));
        item.setDescripcion(rs.getString("descripcion"));
        return item;
    }
}
