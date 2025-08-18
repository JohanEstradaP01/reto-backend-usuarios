package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.model.User;

public interface IUserServicePort {

    public User getUser(Long id);

    public void registerUser(User user) throws OwnerAlreadyExist;

    public User getUserByEmail(String email);

}
