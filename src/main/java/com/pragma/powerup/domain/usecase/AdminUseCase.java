package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.service.IUserRegistrationService;

import java.time.LocalDate;
import java.time.Period;

public class AdminUseCase implements IAdminServicePort {

    private final IUserRegistrationService userRegistrationService;

    public AdminUseCase(IUserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void createOwner(User owner) throws UnderageOwnerException, OwnerAlreadyExist {
        owner.setRole(Role.OWNER);
        LocalDate birthDate = owner.getBirthDate();
        if (Period.between(birthDate, LocalDate.now()).getYears() < 18){
            throw new UnderageOwnerException();
        }
        userRegistrationService.registerUser(owner);
    }

}
