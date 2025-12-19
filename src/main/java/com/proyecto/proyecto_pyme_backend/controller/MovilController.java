package com.proyecto.proyecto_pyme_backend.controller;

import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
import com.proyecto.proyecto_pyme_backend.security.AuthenticatedUserProvider;
import com.proyecto.proyecto_pyme_backend.service.MovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
