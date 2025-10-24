package com.proyecto.proyecto_pyme_backend.dto.Api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //Getters y setters
@AllArgsConstructor //constructor
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;
}
