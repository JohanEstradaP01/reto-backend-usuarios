package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.User;

public interface IUserServicePort {

    public User getUser(Long id);

    public void registerUser(User user);

    public User getUserByEmail(String email);

}
