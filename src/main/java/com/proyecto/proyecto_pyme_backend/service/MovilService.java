package com.proyecto.proyecto_pyme_backend.service;

import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.repository.UserMovilRepository;
import com.proyecto.proyecto_pyme_backend.request.RegisterUsuRequest;
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

}
