package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {

    List<User> findAll();
    User findById(int id);
    User save(User user);
    User update(User user);
    User delete(int id);

}
