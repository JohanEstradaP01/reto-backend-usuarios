package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import com.pragma.powerup.domain.model.User;

public interface IAdminServicePort {

    void createOwner(User owner) throws UnderageOwnerException, OwnerAlreadyExist;

}
