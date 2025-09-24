package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import com.arka.arkahv.domain.model.RefreshToken;
import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {
    RefreshTokenEntity RefreshTokenToRefreshTokenEntity(RefreshToken refreshToken);

    RefreshToken RefreshTokenEntityToRefreshToken(RefreshTokenEntity refreshTokenEntity);
}
