package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;

public interface IAUserHandler {

    public UserResponseDto geUser(Long id);

    public UserResponseDto getUserByEmail(String email);

    public void createUser(UserRequestDto userRequestDto);

}
