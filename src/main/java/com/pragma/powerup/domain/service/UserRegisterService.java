package com.pragma.powerup.domain.service;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IPasswordEncryptor;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

public class UserRegisterService implements IUserRegistrationService{

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncryptor passwordEncryptor;

    public UserRegisterService(IUserPersistencePort userPersistencePort, IPasswordEncryptor passwordEncryptor){
        this.userPersistencePort = userPersistencePort;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public void registerUser(User user) throws OwnerAlreadyExist {
        user.setPassword(passwordEncryptor.encrypt(user.getPassword()));
        if (userPersistencePort.findByEmail(user.getEmail()) != null){
            throw new OwnerAlreadyExist();
        }
        userPersistencePort.saveUser(user);
    }

    @Override
    public void registerUser(User user, Role role) throws OwnerAlreadyExist {
        user.setRole(role);
        user.setPassword(passwordEncryptor.encrypt(user.getPassword()));
        if (userPersistencePort.findByEmail(user.getEmail()) != null){
            throw new OwnerAlreadyExist();
        }
        userPersistencePort.saveUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userPersistencePort.getUser(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

}
