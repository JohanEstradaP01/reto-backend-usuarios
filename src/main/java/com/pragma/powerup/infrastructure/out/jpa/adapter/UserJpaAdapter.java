package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("Lanzar error");
        }
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        return userOptional.map(userEntityMapper::toUser).orElse(null);
    }

}
