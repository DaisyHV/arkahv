package com.arka.arkahv.infraestructure.adapter.out.persistence;

import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.domain.port.out.UserRepositoryPort;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.UserEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.mapper.UserMapper;
import com.arka.arkahv.infraestructure.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    //private  UserEntity userEntity;

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
}
