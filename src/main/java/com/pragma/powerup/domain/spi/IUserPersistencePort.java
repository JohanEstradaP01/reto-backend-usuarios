package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

public interface IUserPersistencePort {

    public User getUser(Long id);

    void saveUser(User user);

    User findByEmail(String email);

}
