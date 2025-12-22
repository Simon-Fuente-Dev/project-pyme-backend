package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.Bcrypt;
import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.UsuarioDto;
import com.proyecto.proyecto_pyme_backend.mapper.UsuMovilRowMapper;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserMovilRepository {
    @Autowired
    private ConsultaGenerica consultaGenerica;

    public ResponseEntity<ApiResponse<Integer>> registrarUsuario(RegisterUsuRequest request) {

        Boolean usuExiste = validaUsuarioMovil(request.getNomUsuario());
        System.out.println("usuExiste: " + usuExiste);
        if (usuExiste) {
            System.out.println("usuExiste: " + usuExiste);
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "El usuario " + request.getNomUsuario() + " ya existe, porfavor intente otro", 0)
            );
        }
        //Validar que el correo NO exista en la base de datos
        Boolean correoExiste = validaCorreoMovil(request.getEmail());
        if (correoExiste) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "El email " + request.getEmail() + " ya existe, porfavor intente otro", 0)
            );
        }

        Integer idUsuario = insertarUsuarioMovil(request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Usuario Registrado con exito!", 1)
        );
    }

    public Boolean validaUsuarioMovil(String nomUsuario) {
        System.out.println(nomUsuario);
        String query = """
                  SELECT COUNT(*)
                  FROM tbl_usuario
                  WHERE upper(nom_usuario) = :nomUsuario;
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nomUsuario", nomUsuario.toUpperCase());
        Integer count = consultaGenerica.valorUnico(query, parametros, Integer.class);
        return count != null && count > 0;
    }

    public Boolean validaCorreoMovil(String correo) {
        String query = """
                SELECT COUNT(*)
                FROM tbl_usuario
                WHERE upper(email) = :correo;
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("correo", correo.toUpperCase());
        Integer count = consultaGenerica.valorUnico(query, parametros, Integer.class);
        return count != null && count > 0;
    }

    public Integer insertarUsuarioMovil(RegisterUsuRequest request) {
        String sqlUsu = """
                INSERT INTO tbl_usuario(nom_usuario, email, password, id_tipo_usuario, activo,
                                        pnombre, snombre, appaterno, apmaterno)
                                 VALUES(:nom_usuario, :email, :password, 4, true,
                                        :pnombre, :snombre, :appaterno, :apmaterno);
                
                """;
        String hashPass = Bcrypt.encode(request.getPassword());
        Map<String, Object> paramsUsu = new HashMap<>();
        paramsUsu.put("nom_usuario", request.getNomUsuario());
        paramsUsu.put("email", request.getEmail());
        paramsUsu.put("password", hashPass);
        paramsUsu.put("pnombre", request.getPnombre());
        paramsUsu.put("snombre", request.getSnombre());
        paramsUsu.put("appaterno", request.getAppaterno());
        paramsUsu.put("apmaterno", request.getApmaterno());

        Integer idUsuario = consultaGenerica.insertarYObtenerId(sqlUsu, paramsUsu, "id_usuario");
        return idUsuario;
    }

    public UsuarioDto validarUsuarioMovil(String username) {
        String sql = """
                    SELECT id_usuario,
                           nom_usuario,
                           password
                    FROM tbl_usuario
                    WHERE nom_usuario = :username 
                        AND id_tipo_usuario = 4
                """;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("username", username);
        return consultaGenerica.objetoUnico(sql, parametros, new UsuMovilRowMapper());
    }
}
