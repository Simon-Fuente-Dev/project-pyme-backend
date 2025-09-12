package com.proyecto.proyecto_pyme_backend.controller;


import com.proyecto.proyecto_pyme_backend.dto.RegionDto;
import com.proyecto.proyecto_pyme_backend.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/web-api")
public class WebController {
    @Autowired
    private WebService webService;

    @GetMapping("/obtener-regiones")
    public List<RegionDto> obtenerRegiones()
    {
        return webService.listarRegiones();
    }
}
