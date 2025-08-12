package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.model.User;

public interface IOwnerUseCase {

    public void createEmployee(User employee, String ownerEmail) throws OwnerAlreadyExist;

}
