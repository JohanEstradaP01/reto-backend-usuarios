package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOwnerUseCase;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.UserNotIsOwnerException;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.service.IUserRegistrationService;
import com.pragma.powerup.domain.spi.IRestaurantConsultPort;

import java.util.Objects;

public class OwnerUseCase implements IOwnerUseCase {

    private final IUserRegistrationService userRegistrationService;
    private final IRestaurantConsultPort restaurantConsultPort;

    public OwnerUseCase(IUserRegistrationService userRegistrationService, IRestaurantConsultPort restaurantConsultPort){
        this.userRegistrationService = userRegistrationService;
        this.restaurantConsultPort = restaurantConsultPort;
    }

    @Override
    public void createEmployee(User employee, String ownerEmail) throws OwnerAlreadyExist {
        User owner = userRegistrationService.getUserByEmail(ownerEmail);
        Long restaurantOwnerId = restaurantConsultPort.consultRestaurantOwner(employee.getRestaurantId());
        if (owner.getRole() != Role.OWNER || !Objects.equals(String.valueOf(restaurantOwnerId), owner.getDocumentNumber())) {
            throw new UserNotIsOwnerException();
        }
        userRegistrationService.registerUser(employee, Role.EMPLOYEE);
    }

}
