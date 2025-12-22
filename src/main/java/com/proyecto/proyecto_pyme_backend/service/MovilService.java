package com.proyecto.proyecto_pyme_backend.service;

import com.proyecto.proyecto_pyme_backend.Utils.Bcrypt;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.AuthResponseDto;
import com.proyecto.proyecto_pyme_backend.dto.AuthResponseDtoMovil;
import com.proyecto.proyecto_pyme_backend.dto.UsuarioDto;
import com.proyecto.proyecto_pyme_backend.repository.UserMovilRepository;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
import com.proyecto.proyecto_pyme_backend.request.UsuAuthRequest;
import com.proyecto.proyecto_pyme_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovilService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMovilRepository userMovilRepository;

    public ResponseEntity<ApiResponse<Integer>> registrarUsuario(RegisterUsuRequest request) {
        return userMovilRepository.registrarUsuario(request);
    }

    public ResponseEntity<ApiResponse<AuthResponseDtoMovil>> validarUsuario(UsuAuthRequest request) {
        UsuarioDto usuario = userMovilRepository.validarUsuarioMovil(request.getUsername());

        if(usuario==null){
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Usuario no existe", null)
            );
        }

        boolean valido = Bcrypt.checkPassword(request.getPassword(), usuario.getPassword());
        if (!valido) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Credenciales incorrectas", null)
            );
        }

        String token = jwtUtil.generateTokenMovil(usuario);

        AuthResponseDtoMovil authResponse = new AuthResponseDtoMovil(
                token,
                usuario.getId_usuario()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Login exitoso", authResponse)
        );
    }

}
