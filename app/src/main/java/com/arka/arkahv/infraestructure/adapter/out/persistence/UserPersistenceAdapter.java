package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.RespuestaDeInicioDeSesion;
import com.arka.arkahv.domain.model.SolicitudDeInicioDeSesion;
import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.domain.port.out.UserRepositoryPort;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.UserEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.UserMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.UserRepository;
import com.arka.arkahv.infraestructure.security.DetallesUsuario;
import com.arka.arkahv.infraestructure.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;


@RequiredArgsConstructor
@Service
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private DetallesUsuario detallesUsuario;

    @Autowired
    public UserPersistenceAdapter(UserRepository repository,
                           @Lazy PasswordEncoder passwordEncoder,
                           UserMapper mapper,
                           JWTUtils jwtUtils,
                           @Lazy AuthenticationManager authenticationManager,
                           @Lazy DetallesUsuario detallesUsuario) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.detallesUsuario = detallesUsuario;}

    @Override
    public User userByEmail(String email) {
        return mapper.userEntityToUser(repository.findUserByEmailAddress(email));
    }


    @Override
    public User save(User user) {
        if(!repository.existsByEmailAddress(user.getEmailAddress())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            return mapper.userEntityToUser(repository.save(mapper.userToUserEntity(user)));

        }
     else {
        throw new RuntimeException("Usuario ya existe");
    }

    }

    public ResponseEntity<?> inicioSesion(SolicitudDeInicioDeSesion solicitudInicio) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(solicitudInicio.getEmail(), solicitudInicio.getPassword());

        try {
            Authentication authentication = authenticationManager
                    .authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            detallesUsuario = (DetallesUsuario) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(detallesUsuario);
            return ResponseEntity.ok(new RespuestaDeInicioDeSesion(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new RespuestaDeInicioDeSesion("Error : usuario o contraseña errónea"));
        }
    }

    @Override
    public ResponseEntity<?> obtenerUsuarioAutenticado() {
        DetallesUsuario detallesUsuario=(DetallesUsuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(detallesUsuario.getUsername());
    }
}
