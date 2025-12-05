package com.proyecto.proyecto_pyme_backend.repository;

import com.proyecto.proyecto_pyme_backend.Utils.Bcrypt;
import com.proyecto.proyecto_pyme_backend.Utils.ConsultaGenerica;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Repository
@Transactional
public class RegisterUsuRepository {

    @Autowired
    private ConsultaGenerica consultaGenerica;

    public ResponseEntity<ApiResponse<Integer>> registrarUsuarioPyme(RegisterUsuRequest request) {
        try {
            //Primero validamos si el usuario ingresado existe!
            Boolean usuExiste = validaUsuarioPyme(request.getNomUsuario());
            if(usuExiste) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "El usuario "+request.getNomUsuario()+" ya existe, porfavor intente otro", 1)
                );
            }

            //Validar que el correo NO exista en la base de datos
            Boolean correoExiste = validaCorreoPyme(request.getEmail());
            if(correoExiste) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "El email "+request.getEmail()+" ya existe, porfavor intente otro", 2));
            }

            //Insertar usuario y pyme, relaciona ambas tablas y retorna el id de la pyme
            Integer idPyme = insertaUsuarioPyme(request);

            insertaSubServicios(idPyme, request);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Registrando usuario... "+ idPyme, 1)
            );
        } catch (Exception e) {
            // logueas si quieres
            System.err.println("Error al registrar usuario: " + e.getMessage());

            // y relanzas para que Spring haga rollback
            throw e;

        }
    };

    public Boolean validaUsuarioPyme(String nomUsuario) {
        System.out.println(nomUsuario);
        String query = """
                  SELECT COUNT(*)
                  FROM tbl_usuario
                  WHERE upper(nom_usuario) = :nomUsuario;
                """;
        Map<String,Object> params = Map.of("nomUsuario", nomUsuario.toUpperCase());
        Integer count = consultaGenerica.valorUnico(query, params, Integer.class);
        return count != null && count > 0;

    }

    public Boolean validaCorreoPyme(String correo) {
        String query = """
                SELECT COUNT(*)
                FROM tbl_usuario
                WHERE upper(email) = :correo;
                """;
        Map<String,Object> params = Map.of("correo", correo.toUpperCase());
        Integer count = consultaGenerica.valorUnico(query, params, Integer.class);
        return count != null && count > 0;
    }

    //Retorna el id de la pyme
    public Integer insertaUsuarioPyme(RegisterUsuRequest request) {
        String sqlUsu = """
                INSERT INTO tbl_usuario(nom_usuario, email, password, id_tipo_usuario, activo,
                                        pnombre, snombre, appaterno, apmaterno)
                                 VALUES(:nom_usuario, :email, :password, 2, true,
                                        :pnombre, :snombre, :appaterno, :apmaterno);
                
                """;
        String hashPass = Bcrypt.encode(request.getPassword());
        Map<String,Object> paramsUsu = Map.of(
                "nom_usuario", request.getNomUsuario(),
                "email", request.getEmail(),
                "password", hashPass,
                "pnombre", request.getPnombre(),
                "snombre", request.getSnombre(),
                "appaterno", request.getAppaterno(),
                "apmaterno", request.getApmaterno()
        );
        Integer idUsuario = consultaGenerica.insertarYObtenerId(sqlUsu, paramsUsu, "id_usuario");

        String sqlPyme = """
                INSERT INTO tbl_pyme(nombre_pyme, direccion, id_comuna, descripcion_pyme)
                            values(:nombre_pyme, :direccion, :id_comuna, :descripcion_pyme);
                """;
        Map<String,Object> paramsPyme = Map.of(
                "nombre_pyme", request.getNomPyme(),
                "direccion", request.getDireccion(),
                "id_comuna", request.getComuna(),
                "descripcion_pyme", request.getDescPyme()
        );

        Integer idPyme = consultaGenerica.insertarYObtenerId(sqlPyme, paramsPyme, "id_pyme");

        String sqlUsuPyme = """
                INSERT INTO tbl_usuario_pyme(id_pyme, id_usuario)
                                      VALUES(:id_pyme, :id_usuario)
                """;
        Map<String,Object> paramsUsuPyme = Map.of(
                "id_pyme", idPyme,
                "id_usuario", idUsuario
        );

        Integer usuarioPymeId = consultaGenerica.insertarYObtenerId(sqlUsuPyme, paramsUsuPyme, "id_usu_pyme");
        return idPyme;
    }

    public void insertaSubServicios(Integer idPyme, RegisterUsuRequest request) {
        String sqlServ = """
                INSERT INTO tbl_tipo_serv_pyme(id_pyme, id_tipo_servicio)
                                        VALUES(:id_pyme, :id_tipo_servicio)
                """;
        Map<String,Object> paramsServ = Map.of(
                "id_pyme", idPyme,
                "id_tipo_servicio", request.getTipoServicio());
        consultaGenerica.insertarYObtenerId(sqlServ, paramsServ, "id_serv_pyme");

        String sqlSubServ = """
                INSERT INTO tbl_sub_serv_pyme(id_pyme, id_sub_servicio)
                                       VALUES(:id_pyme, :id_sub_servicio);
                """;
        for (Integer idSubServicio : request.getSubServicio()) {
            Map<String,Object> paramsSubServ = Map.of(
                    "id_pyme", idPyme,
                    "id_sub_servicio", idSubServicio
            );

            consultaGenerica.insertarYObtenerId(sqlSubServ, paramsSubServ, "id_sub_serv_pyme");
        }
    }
}
