package com.proyecto.grupo_umg2024.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.grupo_umg2024.model.entity.BaseResponse;
import com.proyecto.grupo_umg2024.model.entity.User;
import com.proyecto.grupo_umg2024.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/proyecto/")
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("admin/see")
    public List<User> getDataList() {
        return service.getDataList();
    }

    @DeleteMapping("eliminar/{id}")
    public List<User> deleteCatedratico(@PathVariable Long id) {
        User find = service.getFindUncle(id);
        if (find != null) {
            service.deleteFind(find);
        }
        return null;
    }

    @PostMapping("user/usuario")
    public ResponseEntity<BaseResponse> login(@RequestParam String token) {
        try {
            return ResponseEntity.ok(BaseResponse.builder().code("200").message("Inicio Correctamente")
                    .entity(service.obtenerUser(token)).build());
        } catch (Exception e) {
            return ResponseEntity.ok(
                    BaseResponse.builder().code("400").message("Usuario no Existe o Contraseña es invalida").build());
        }
    }

}
