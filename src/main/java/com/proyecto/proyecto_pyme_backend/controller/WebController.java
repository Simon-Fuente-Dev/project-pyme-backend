package com.proyecto.proyecto_pyme_backend.controller;


import com.proyecto.proyecto_pyme_backend.dto.*;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.mapper.TipoRedRowMapper;
import com.proyecto.proyecto_pyme_backend.request.AgregarRedRequest;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
import com.proyecto.proyecto_pyme_backend.request.UsuAuthRequest;
import com.proyecto.proyecto_pyme_backend.security.AuthenticatedUserProvider;
import com.proyecto.proyecto_pyme_backend.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web-api")
public class WebController {
    @Autowired
    private WebService webService;
    @Autowired
    private AuthenticatedUserProvider userProvider;

    //Sin necesidad de logueo
    @PostMapping("auth-usuario")
    public ResponseEntity<ApiResponse<AuthResponseDto>> authUsuario(@RequestBody UsuAuthRequest usuAuthRequest) {
        return webService.validarUsuario(usuAuthRequest.getUsername(), usuAuthRequest.getPassword());
    }

    @GetMapping("/obtener-regiones")
    public List<RegionDto> obtenerRegiones() {
        return webService.listarRegiones();
    }

    @GetMapping("/obtener-comunas/{id_region}")
    public List<ComunaDto> obtenerComunas(@PathVariable Integer id_region) {
        return webService.listarComunas(id_region);
    }

    @GetMapping("/obtener-tipo-serv")
    public List<TipoServDto> obtenerTipoServicio() {
        return webService.listarTipoServ();
    }

    @GetMapping("obtener-sub-servicio/{id_servicio}")
    public List<SubServicioDto> obtenerSubServicio(@PathVariable Integer id_servicio) {
        return webService.listarSubServicio(id_servicio);
    }

    @PostMapping("/registar-pyme")
    public ResponseEntity<ApiResponse<Integer>> registrarUsuarioPyme(@RequestBody RegisterUsuRequest request) {
        return webService.registrarUsuarioPyme(request);
    }




    @GetMapping("/obtener-tipo-item")
    @PreAuthorize("isAuthenticated()")
    public List<ItemDto> obtenerItem() {
        return webService.listarTipoItem();
    }

    @GetMapping("/get-serv-pyme")
    @PreAuthorize("isAuthenticated()")
    public List<TipoServDto> obtenerServPyme() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.listarServPyme(id_pyme);
    }

    @GetMapping("/get-sub-serv-pyme")
    @PreAuthorize("isAuthenticated()")
    public List<SubServicioDto> obtenerSubServPyme() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.listarSubServPyme(id_pyme);
    }

    @GetMapping("/get-tipo-red")
    @PreAuthorize("isAuthenticated()")
    public List<TipoRedDto> obtenerTipoRed() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.listarTipoRed(id_pyme);
    }

    @PostMapping("agregar-red-pyme")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Integer>> agregarRedPyme(@RequestBody AgregarRedRequest request) {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.agregarRedPyme(request, id_pyme);
    }

    @GetMapping("get-redes-pyme")
    @PreAuthorize("isAuthenticated()")
    public List<RedPymeDto> obtenerRedesPyme() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.obtenerRedesPyme(id_pyme);
    }




}





















