package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.SolicitudDeInicioDeSesion;
import com.arka.arkahv.domain.model.SolicitudRefreshToken;
import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.domain.port.in.UserUseCase;
import com.arka.arkahv.domain.port.out.ProductRepositoryPort;
import com.arka.arkahv.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class UserApplicationService implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepositoryPort.userByEmail(email);
    }

    @Override
    public ResponseEntity<?> inicioSesion(SolicitudDeInicioDeSesion SolicitudInicio) {
        return userRepositoryPort.inicioSesion(SolicitudInicio);
    }

    @Override
    public ResponseEntity<?> obtenerUsuarioAutenticado() {
        return userRepositoryPort.obtenerUsuarioAutenticado();
    }

    @Override
    public ResponseEntity<?> refreshToken(SolicitudRefreshToken solicitudRefresh){
        return userRepositoryPort.refreshToken(solicitudRefresh);
    }

}
