package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.UserEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.UserMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.UserRepository;
import com.arka.arkahv.infraestructure.security.DetallesUsuario;
import com.arka.arkahv.infraestructure.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserPresistenceAdapterTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper mapper;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private DetallesUsuario detallesUsuario;

    @Mock
    private RefreshTokenAdapter refreshTokenAdapter;

    @InjectMocks
    private UserPersistenceAdapter adapter;

    @Test
    void save_shouldEncodePasswordAndSaveUser_whenEmailDoesNotExist() {
        // Arrange
        User user = new User();
        user.setEmailAddress("test@email.com");
        user.setPassword("plainPassword");

        UserEntity userEntity = new UserEntity();
        UserEntity savedEntity = new UserEntity();
        User savedUser = new User();
        savedUser.setEmailAddress("test@email.com");

        when(repository.existsByEmailAddress(user.getEmailAddress())).thenReturn(false);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(mapper.userToUserEntity(any(User.class))).thenReturn(userEntity);
        when(repository.save(userEntity)).thenReturn(savedEntity);
        when(mapper.userEntityToUser(savedEntity)).thenReturn(savedUser);

        // Act
        User result = adapter.save(user);

        // Assert
        assertNotNull(result);
        assertEquals("test@email.com", result.getEmailAddress());
        verify(passwordEncoder).encode("plainPassword");
        verify(repository).save(userEntity);
    }

    @Test
    void save_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        User user = new User();
        user.setEmailAddress("existing@email.com");

        when(repository.existsByEmailAddress(user.getEmailAddress())).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> adapter.save(user));
        assertEquals("Usuario ya existe", exception.getMessage());
    }

    @Test
    void userByEmail_shouldReturnMappedUser() {
        // Arrange
        String email = "user@email.com";
        UserEntity userEntity = new UserEntity();
        User user = new User();
        user.setEmailAddress(email);

        when(repository.findUserByEmailAddress(email)).thenReturn(userEntity);
        when(mapper.userEntityToUser(userEntity)).thenReturn(user);

        // Act
        User result = adapter.userByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmailAddress());
    }
}
