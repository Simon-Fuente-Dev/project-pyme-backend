package com.proyecto.proyecto_pyme_backend.service;
import com.proyecto.proyecto_pyme_backend.dto.Item.ItemDto;
import com.proyecto.proyecto_pyme_backend.repository.Item.ItemRepository;
import com.proyecto.proyecto_pyme_backend.security.JwtUtil;
import com.proyecto.proyecto_pyme_backend.Utils.Bcrypt;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.PymeUsuDto;
import com.proyecto.proyecto_pyme_backend.dto.Region.RegionDto;
import com.proyecto.proyecto_pyme_backend.dto.AuthResponseDto;

import com.proyecto.proyecto_pyme_backend.repository.Region.RegionRepository;
import com.proyecto.proyecto_pyme_backend.repository.Usuario.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private JwtUtil jwtUtil;


    public List<RegionDto> listarRegiones() {
        return regionRepository.obtenerRegiones();
    }

    public ResponseEntity<ApiResponse<AuthResponseDto>> validarUsuario(String username, String password) {
        PymeUsuDto usuario = userRepository.obtenerUsuPyme(username);

        if (usuario == null) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Usuario no existe", null)
            );
        }

        boolean valido = Bcrypt.checkPassword(password, usuario.getPassword());
        if (!valido) {
            return ResponseEntity.ok(
                    new ApiResponse<>(false, "Credenciales incorrectas", null)
            );
        }

        String token = jwtUtil.generateToken(usuario);

        AuthResponseDto authResponse = new AuthResponseDto(
                token,
                usuario.getId_usuario(),
                usuario.getId_pyme(),
                usuario.getNombre_pyme()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Login exitoso", authResponse)
        );
    }

    public List<ItemDto> listarTipoItem() {
        return itemRepository.listarTipoItem();
    }


}
