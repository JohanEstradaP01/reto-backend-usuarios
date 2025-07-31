package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userServicePort){
        this.userPersistencePort = userServicePort;
    }

    @Override
    public void registerUser(User user) {
        return;
    }


}
