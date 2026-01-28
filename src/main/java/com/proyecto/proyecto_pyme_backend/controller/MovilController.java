package com.proyecto.proyecto_pyme_backend.controller;

import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.AuthResponseDto;
import com.proyecto.proyecto_pyme_backend.dto.AuthResponseDtoMovil;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
import com.proyecto.proyecto_pyme_backend.request.UsuAuthRequest;
import com.proyecto.proyecto_pyme_backend.security.AuthenticatedUserProvider;
import com.proyecto.proyecto_pyme_backend.service.MovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movil-api")
public class MovilController {
    @Autowired
    private AuthenticatedUserProvider userProvider;

    @Autowired
    private MovilService movilService;

    @PostMapping("/registrar-usuario")
    public ResponseEntity<ApiResponse<Integer>> registrarUsuario(@RequestBody RegisterUsuRequest request) {
        return movilService.registrarUsuario(request);
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<ApiResponse<AuthResponseDtoMovil>> iniciarSesion(@RequestBody UsuAuthRequest request) {
        System.out.println(request);
        return movilService.validarUsuario(request);
    }

    //Aqui iran las rutas que requieran que el usuario este logeado y que mande el token
    @GetMapping("/mis-datos")
    @PreAuthorize("isAuthenticated()")
    public Integer obtenerDatosMovil() {
        // El userProvider extrae el ID del token que Axios envía automáticamente
        Integer idUsuario = userProvider.getIdUsuario();
        return idUsuario;
    }
}
