package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.RefreshToken;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.RefreshTokenMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.UserMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.RefreshTokenJpaRepository;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenAdapter {

    private RefreshTokenMapper mapper;

    private UserMapper mapperUser;

    @Autowired
    public RefreshTokenAdapter(
            RefreshTokenMapper mapper,
            UserMapper mapperUser,
            RefreshTokenJpaRepository repositorioRefreshToken,
            UserRepository repositorioUsuario
    ) {
        this.mapper = mapper;
        this.mapperUser = mapperUser;
        this.repositorioRefreshToken = repositorioRefreshToken;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Value("${jwt-refresh-expiration-ms}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenJpaRepository repositorioRefreshToken;

    @Autowired
    private UserRepository repositorioUsuario;

    public Optional<RefreshToken> encontrarToken(String token) {
        return repositorioRefreshToken.findByToken(token)
                .map(mapper::RefreshTokenEntityToRefreshToken);
    }

    public RefreshToken crearRefreshToken(int usuarioId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsuario(mapperUser.userEntityToUser(repositorioUsuario.findById(usuarioId).get()));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = mapper.RefreshTokenEntityToRefreshToken(repositorioRefreshToken.save(mapper.RefreshTokenToRefreshTokenEntity(refreshToken)));
        return refreshToken;
    }

    public boolean verificarExpiracion(RefreshToken token){
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            repositorioRefreshToken.delete(mapper.RefreshTokenToRefreshTokenEntity(token));
            return false;
        }
        return true;
    }

    @Transactional
    public int borrarParaUsuario(int usuarioId) {
        return repositorioRefreshToken.deleteByUsuario(repositorioUsuario.findById(usuarioId).get());
    }

}
