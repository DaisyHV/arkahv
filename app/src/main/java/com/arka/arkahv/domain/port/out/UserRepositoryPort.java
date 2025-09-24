package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.SolicitudDeInicioDeSesion;
import com.arka.arkahv.domain.model.SolicitudRefreshToken;
import com.arka.arkahv.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface UserRepositoryPort {

    User userByEmail(String email);
    User save(User user);
    ResponseEntity<?> inicioSesion(SolicitudDeInicioDeSesion solicitudDeInicioDeSesion);
    ResponseEntity<?> obtenerUsuarioAutenticado();
    ResponseEntity<?> refreshToken(SolicitudRefreshToken refreshToken);

}
