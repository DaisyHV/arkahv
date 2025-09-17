package com.arka.arkahv.infraestructure.adapter.in.web;

import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.domain.port.in.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping("/registro")
    public User crearUsuario(@RequestBody User objetoUsuario) {
        return userUseCase.createUser(objetoUsuario);
    }
}
