package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User getUser(Long id) {
        return userPersistencePort.getUser(id);
    }

    @Override
    public void registerUser(User user) {
    }
}
