package com.arka.arkahv.domain.port.in;

import com.arka.arkahv.domain.model.SolicitudDeInicioDeSesion;
import com.arka.arkahv.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface UserUseCase {
    User createUser(User user);
    User getUserByEmail(String email);
    ResponseEntity<?> inicioSesion(SolicitudDeInicioDeSesion SolicitudInicio);
    ResponseEntity<?> obtenerUsuarioAutenticado();
}

