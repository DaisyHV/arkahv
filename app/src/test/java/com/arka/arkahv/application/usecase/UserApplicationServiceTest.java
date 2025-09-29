package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.RespuestaDeInicioDeSesion;
import com.arka.arkahv.domain.model.SolicitudDeInicioDeSesion;
import com.arka.arkahv.domain.model.SolicitudRefreshToken;
import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserApplicationServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserApplicationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_delegatesToRepository() {
        User user = new User();
        when(userRepositoryPort.save(user)).thenReturn(user);

        User result = service.createUser(user);

        assertEquals(user, result);
        verify(userRepositoryPort).save(user);
    }

    @Test
    void getUserByEmail_delegatesToRepository() {
        User user = new User();
        when(userRepositoryPort.userByEmail("test@mail.com")).thenReturn(user);

        User result = service.getUserByEmail("test@mail.com");

        assertEquals(user, result);
        verify(userRepositoryPort).userByEmail("test@mail.com");
    }

    @Test
    void inicioSesion_delegatesToRepository() {
        // Arrange
        SolicitudDeInicioDeSesion solicitud = new SolicitudDeInicioDeSesion("correo@test.com", "1234");
        RespuestaDeInicioDeSesion respuesta = new RespuestaDeInicioDeSesion("jwt", "refresh", "correo@test.com");

        ResponseEntity response = ResponseEntity.ok(respuesta);

        when(userRepositoryPort.inicioSesion(solicitud)).thenReturn(response);

        // Act
        ResponseEntity<?> result = service.inicioSesion(solicitud);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    void obtenerUsuarioAutenticado_delegatesToRepository() {
        ResponseEntity response = ResponseEntity.ok().build();
        when(userRepositoryPort.obtenerUsuarioAutenticado()).thenReturn(response);

        ResponseEntity<?> result = service.obtenerUsuarioAutenticado();

        assertEquals(response, result);
        verify(userRepositoryPort).obtenerUsuarioAutenticado();
    }

    @Test
    void refreshToken_delegatesToRepository() {
        SolicitudRefreshToken solicitud = new SolicitudRefreshToken();
        ResponseEntity response = ResponseEntity.ok().build();
        when(userRepositoryPort.refreshToken(solicitud)).thenReturn(response);

        ResponseEntity<?> result = service.refreshToken(solicitud);

        assertEquals(response, result);
        verify(userRepositoryPort).refreshToken(solicitud);
    }
}