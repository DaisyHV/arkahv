package com.arka.arkahv.application.usecase;

import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.domain.port.in.UserUseCase;
import com.arka.arkahv.domain.port.out.ProductRepositoryPort;
import com.arka.arkahv.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

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
}
