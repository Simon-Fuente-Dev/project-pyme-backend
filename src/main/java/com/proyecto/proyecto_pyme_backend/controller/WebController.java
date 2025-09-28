package com.proyecto.proyecto_pyme_backend.controller;


import com.proyecto.proyecto_pyme_backend.Utils.Bcrypt;
import com.proyecto.proyecto_pyme_backend.dto.ApiResponse;
import com.proyecto.proyecto_pyme_backend.dto.PymeUsuDto;
import com.proyecto.proyecto_pyme_backend.dto.RegionDto;
import com.proyecto.proyecto_pyme_backend.repository.UserRepository;
import com.proyecto.proyecto_pyme_backend.request.UsuAuthRequest;
import com.proyecto.proyecto_pyme_backend.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web-api")
public class WebController {
    @Autowired
    private WebService webService;


//    @PostMapping("/hash-pass")
//    public String hashPass(String password) {
//        String passHash = Bcrypt.encode(password);
//        System.out.println("Hash pass: " + passHash);
//        return passHash;
//    }
//
//    @GetMapping("/verificar-pass")
//    public boolean verificarPass(String pass) {
//        String passHash = "$2a$10$HMRlVzvciEQOCtDeMtMD7.iuwKWYtwgN/7d5s0X.0e/DJwmw.dbIq";
//        return Bcrypt.checkPassword(pass, passHash);
//    }

    @PostMapping("auth-usuario")
    public ResponseEntity<ApiResponse<String>> authUsuario(@RequestBody UsuAuthRequest usuAuthRequest) {
        System.out.println(webService.obtenerUsuarioXusername(usuAuthRequest.getUsername()));
        ApiResponse<String> response = new ApiResponse<>(
                true,
                "Contraseña hasheada correctamente",
                "pass: " + usuAuthRequest.getPassword() + " usuario: " + usuAuthRequest.getUsername()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/obtener-regiones")
    public List<RegionDto> obtenerRegiones()
    {
        return webService.listarRegiones();
    }
}
