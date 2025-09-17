package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.User;

public interface UserRepositoryPort {

    User userByEmail(String email);
    User save(User user);


}
