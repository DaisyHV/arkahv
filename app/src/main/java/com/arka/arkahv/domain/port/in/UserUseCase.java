package com.arka.arkahv.domain.port.in;

import com.arka.arkahv.domain.model.User;

public interface UserUseCase {
    User createUser(User user);
    User getUserByEmail(String email);
}
