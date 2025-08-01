package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.exception.EmailFormatNoValid;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.PhoneNumberFormatNoValid;
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
    public void createOwner(User owner) throws UnderageOwnerException, OwnerAlreadyExist, EmailFormatNoValid, PhoneNumberFormatNoValid {
        LocalDate birthDate = owner.getBirthDate();
        String email = owner.getEmail();
        String phone = owner.getPhone();

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new EmailFormatNoValid();
        }
        if (Period.between(birthDate, LocalDate.now()).getYears() < 18){
            throw new UnderageOwnerException();
        }
        if (!phone.matches("^\\+?\\d{1,13}$")) {
            throw new PhoneNumberFormatNoValid();
        }

        owner.setRole(Role.OWNER);
        userRegistrationService.registerUser(owner, Role.OWNER);
    }

}
