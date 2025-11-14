package com.proyecto.proyecto_pyme_backend.service;
import com.proyecto.proyecto_pyme_backend.dto.*;
import com.proyecto.proyecto_pyme_backend.repository.*;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
import com.proyecto.proyecto_pyme_backend.security.JwtUtil;
import com.proyecto.proyecto_pyme_backend.Utils.Bcrypt;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;

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
    private  TipoServRepository tipoServRepository;

    @Autowired
    private SubServicioRepository subServicioRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegisterUsuRepository registerUsuRepository;

    @Autowired
    private JwtUtil jwtUtil;



    public List<RegionDto> listarRegiones() {
        return regionRepository.obtenerRegiones();
    }

    public List<ComunaDto> listarComunas(Integer id_region) { return comunaRepository.obtenerComunas(id_region);}

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

    public ResponseEntity<ApiResponse<Integer>> registrarUsuarioPyme(RegisterUsuRequest request) {
        return registerUsuRepository.registrarUsuarioPyme(request);
    }

    public List<ItemDto> listarTipoItem() {
        return itemRepository.listarTipoItem();
    }

    public List<TipoServDto>listarTipoServ() {

        return tipoServRepository.listarTipoServ();
    }

    public List<SubServicioDto> listarSubServicio(Integer id_servicio) {
        return subServicioRepository.listarSubServicio(id_servicio);
    }


}
