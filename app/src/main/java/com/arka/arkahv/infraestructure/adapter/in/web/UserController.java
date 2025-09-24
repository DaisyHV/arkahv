package com.arka.arkahv.infraestructure.adapter.in.web;

import com.arka.arkahv.domain.model.SolicitudDeInicioDeSesion;
import com.arka.arkahv.domain.model.SolicitudRefreshToken;
import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.domain.port.in.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping("/registro")
    public User crearUsuario(@RequestBody User objetoUsuario) {
        return userUseCase.createUser(objetoUsuario);
    }

    @PostMapping("/inicioSesion")
    public ResponseEntity<?> inicioSesion(@RequestBody SolicitudDeInicioDeSesion solicitudInicio) {
        return userUseCase.inicioSesion(solicitudInicio);
    }

    @GetMapping("/prueba")
    public ResponseEntity<?> obtenerUsuarioAutenticado() {
        return userUseCase.obtenerUsuarioAutenticado();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody SolicitudRefreshToken solicitudRefresh) {
        return userUseCase.refreshToken(solicitudRefresh);
    }


}

