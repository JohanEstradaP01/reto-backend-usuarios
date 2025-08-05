package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;

public interface IOwnerHandler {

    public void createEmployee(UserRequestDto userRequestDto) throws OwnerAlreadyExist;

}
