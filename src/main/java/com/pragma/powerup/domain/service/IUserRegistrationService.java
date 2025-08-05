package com.pragma.powerup.domain.service;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;

public interface IUserRegistrationService {

    public void registerUser(User user) throws OwnerAlreadyExist;

    public void registerUser(User user, Role role) throws OwnerAlreadyExist;

    public User getUser(Long id);

}
