package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);

    User findByEmail(String email);

}
