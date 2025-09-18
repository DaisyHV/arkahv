package com.arka.arkahv.infraestructure.security;

import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.infraestructure.adapter.out.persistence.UserPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServicioDetallesUsuario implements UserDetailsService {

    private final UserPersistenceAdapter servicioUsuario;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = servicioUsuario.userByEmail(username);
        return new DetallesUsuario(user);
    }
}
