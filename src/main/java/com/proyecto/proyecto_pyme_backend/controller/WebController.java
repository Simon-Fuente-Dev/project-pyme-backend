package com.proyecto.proyecto_pyme_backend.controller;


import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.AuthResponseDto;
import com.proyecto.proyecto_pyme_backend.dto.ComunaDto;
import com.proyecto.proyecto_pyme_backend.dto.ItemDto;
import com.proyecto.proyecto_pyme_backend.dto.RegionDto;
import com.proyecto.proyecto_pyme_backend.request.UsuAuthRequest;
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


    @PostMapping("auth-usuario")
    public ResponseEntity<ApiResponse<AuthResponseDto>> authUsuario(@RequestBody UsuAuthRequest usuAuthRequest) {
        return webService.validarUsuario(usuAuthRequest.getUsername(), usuAuthRequest.getPassword());
    }

    @GetMapping("/obtener-regiones")
    public List<RegionDto> obtenerRegiones()
    {
        return webService.listarRegiones();
    }

    @GetMapping("/obtener-comunas/{id_region}")
    public List<ComunaDto> obtenerComunas(@PathVariable Integer id_region) {return webService.listarComunas(id_region);}

    @GetMapping("/obtener-data-pyme/{id_pyme}")
    @PreAuthorize("isAuthenticated()")
    public Integer obtenerDataPyme(@PathVariable Integer id_pyme)
    {
        return id_pyme;
    }

    @GetMapping("/obtener-tipo-item")
    @PreAuthorize("isAuthenticated()")
    public List<ItemDto> obtenerItem() {return webService.listarTipoItem();}
}
