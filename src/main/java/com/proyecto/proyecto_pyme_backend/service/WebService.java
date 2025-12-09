package com.proyecto.proyecto_pyme_backend.service;
import com.proyecto.proyecto_pyme_backend.dto.*;
import com.proyecto.proyecto_pyme_backend.repository.*;
import com.proyecto.proyecto_pyme_backend.request.AddEditItemRequest;
import com.proyecto.proyecto_pyme_backend.request.AgregarEditarRedRequest;
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
    private JwtUtil jwtUtil;

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
    private RedRepository redRepository;

    @Autowired
    private PymeRepository pymeRepository;




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

    //Servicios y sub servicios
    public List<TipoServDto>listarTipoServ() {

        return tipoServRepository.listarTipoServ();
    }

    public List<SubServicioDto> listarSubServicio(Integer id_servicio) {
        return subServicioRepository.listarSubServicio(id_servicio);
    }

    public List<TipoServDto> listarServPyme(Integer id_pyme) {
        return tipoServRepository.listarServPyme(id_pyme);
    }

    public List<SubServicioDto> listarSubServPyme(Integer id_pyme) {
        return subServicioRepository.listarSubServPyme(id_pyme);
    }

    ////////REDES SOCIALES
    public List<TipoRedDto> listarTipoRed(Integer id_pyme) {
        return redRepository.listarTipoRed(id_pyme);
    }

    public ResponseEntity<ApiResponse<Integer>> agregarRedPyme(AgregarEditarRedRequest request, Integer id_pyme) {
        return redRepository.agregarRedPyme(request, id_pyme);
    }

    public ResponseEntity<ApiResponse<Integer>> modificarRedPyme(AgregarEditarRedRequest request, Integer id_pyme) {
        return redRepository.modificarRedPyme(request, id_pyme);
    }

    public ResponseEntity<ApiResponse<Integer>> eliminarRedPyme(Integer id_red,Integer id_pyme) {
        return redRepository.eliminarRedPyme(id_red, id_pyme);
    }

    public List<RedPymeDto> obtenerRedesPyme(Integer id_pyme) {
        return redRepository.obtenerRedesPyme(id_pyme);
    }

    ////////Datos de la pyme
    public PymeDto obtenerDataPyme(Integer id_pyme) {
        return pymeRepository.obtenerDataPyme(id_pyme);
    }

    public ResponseEntity<ApiResponse<Integer>> modificarNomPyme(Integer id_pyme, String nombre_pyme) {
        return pymeRepository.modificarNomPyme(id_pyme, nombre_pyme);
    }

    public ResponseEntity<ApiResponse<Integer>> modificarDescPyme(Integer id_pyme, String descripcion_pyme) {
        return pymeRepository.modificarDescPyme(id_pyme, descripcion_pyme);
    }

    /// Item
    public Integer agregarItemPyme(Integer id_pyme, AddEditItemRequest request) {
        return itemRepository.agregarItemPyme(Integer id_pyme, request);
    }
}
