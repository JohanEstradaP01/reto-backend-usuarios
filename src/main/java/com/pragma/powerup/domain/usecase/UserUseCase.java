package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.service.IUserRegistrationService;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserRegistrationService userRegistrationService;

    public UserUseCase(IUserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public User getUser(Long id) {
        return userRegistrationService.getUser(id);
    }

    @Override
    public void registerUser(User user) {
    }

    @Override
    public User getUserByEmail(String email) {
        return userRegistrationService.getUserByEmail(email);
    }
}
