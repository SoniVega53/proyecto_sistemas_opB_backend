package com.proyecto.grupo_umg2024.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
@CrossOrigin(origins = "http://172.18.0.1:4200")
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
public ResponseEntity<List<User>> deleteCatedratico(@PathVariable Long id) {
    User find = service.getFindUncle(id);
    if (find != null) {
        service.deleteFind(find);
        return ResponseEntity.ok(service.getDataList()); // Devuelve la lista actualizada
    } else {
        return ResponseEntity.status(404).body(null); // Usuario no encontrado
    }
}

@PostMapping("usuario")
public ResponseEntity<BaseResponse> login(@RequestParam String token) {
    try {
        UserDetails userDetails = service.obtenerUser(token); // Cambia a UserDetails
        
        if (userDetails != null) {
            // Aquí, crea el objeto User según tus necesidades
            User user = new User(0, userDetails.getUsername(), userDetails.getPassword(), token, token, token, token); // Completa con los campos necesarios

            return ResponseEntity.ok(BaseResponse.builder()
                .code("200")
                .message("Inicio Correcto")
                .entity(user)
                .build());
        } else {
            return ResponseEntity.status(401).body(BaseResponse.builder()
                .code("401")
                .message("Usuario no Existe o Contraseña inválida")
                .build());
        }
    } catch (Exception e) {
        return ResponseEntity.status(500).body(BaseResponse.builder()
            .code("500")
            .message("Error")
            .build());
    }
}
}
