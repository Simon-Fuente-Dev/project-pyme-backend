package com.proyecto.proyecto_pyme_backend.service;

import com.proyecto.proyecto_pyme_backend.dto.RegionDto;
import com.proyecto.proyecto_pyme_backend.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDto> listarRegiones() {
        return regionRepository.obtenerRegiones();
    }
}
