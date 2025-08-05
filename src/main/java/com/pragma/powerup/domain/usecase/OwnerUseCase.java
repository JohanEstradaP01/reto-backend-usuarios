package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOwnerUseCase;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.service.IUserRegistrationService;

public class OwnerUseCase implements IOwnerUseCase {

    private final IUserRegistrationService userRegistrationService;

    public OwnerUseCase(IUserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void createEmployee(User employee) throws OwnerAlreadyExist {
        userRegistrationService.registerUser(employee, Role.EMPLOYEE);
    }
}
