package com.proyecto.proyecto_pyme_backend.controller;


import com.proyecto.proyecto_pyme_backend.dto.*;
import com.proyecto.proyecto_pyme_backend.dto.Api.ApiResponse;
import com.proyecto.proyecto_pyme_backend.request.*;
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


    ///Redes de la pyme
    @GetMapping("get-redes-pyme")
    @PreAuthorize("isAuthenticated()")
    public List<RedPymeDto> obtenerRedesPyme() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.obtenerRedesPyme(id_pyme);
    }


    @GetMapping("/get-tipo-red")
    @PreAuthorize("isAuthenticated()")
    public List<TipoRedDto> obtenerTipoRed() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.listarTipoRed(id_pyme);
    }

    @PostMapping("agregar-editar-red-pyme")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Integer>> agregarEditarRedPyme(@RequestBody AgregarEditarRedRequest request) {
        Integer id_pyme = userProvider.getIdPyme();
        if(request.getIdRedPyme() == 0) {
            return webService.agregarRedPyme(request, id_pyme);
        }else {
            return webService.modificarRedPyme(request, id_pyme);
        }
    }

    @GetMapping("eliminar-red-pyme/{id_red}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Integer>> eliminarRedPyme(@PathVariable Integer id_red) {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.eliminarRedPyme(id_red, id_pyme);

    }

    //Datos de la pyme

    @GetMapping("get-data-pyme")
    @PreAuthorize("isAuthenticated()")
    public PymeDto obtenerDataPyme() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.obtenerDataPyme(id_pyme);
    }

    @PostMapping("modificar-nombre-pyme")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Integer>> modificarNomPyme(@RequestBody ModificarNomPymeRequest request) {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.modificarNomPyme(id_pyme, request.getNombre_pyme());
    }

    @PostMapping("modificar-desc-pyme")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Integer>> modificarDescPyme(@RequestBody ModificarDescPymeRequest request) {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.modificarDescPyme(id_pyme, request.getDescripcion_pyme());
    }


    /// Producto y servicio
    @PostMapping("add-edit-item")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Integer>> agregarEditar(@RequestBody AddEditItemRequest request) {

        Integer id_pyme = userProvider.getIdPyme();
        if(request.getId() == 0) {
            Integer resp = webService.agregarItemPyme(id_pyme, request);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Item agregado con exito!", resp)
            );
        }else {
            Integer resp = webService.editarItemPyme(id_pyme, request);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Item modificado con exito!", resp)
            );
        }

    }

    @GetMapping("get-item-pyme")
    @PreAuthorize("isAuthenticated()")
    public List<ItemPymeDto> obtenerItemPyme() {
        Integer id_pyme = userProvider.getIdPyme();
        return webService.obtenerItemPyme(id_pyme);
    }








}





















