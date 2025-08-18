package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IAUserHandler;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.application.mapper.UserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandler implements IAUserHandler {

    private final UserResponseMapper userResponseMapper;
    private final IUserServicePort userServicePort;

    @Override
    public UserResponseDto geUser(Long id) {
        return userResponseMapper.toUSerResponse(userServicePort.getUser(id));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userResponseMapper.toUSerResponse(userServicePort.getUserByEmail(email));
    }

    @Override
    public void createUser(UserRequestDto userRequestDto) throws OwnerAlreadyExist {
        userServicePort.registerUser(userResponseMapper.toUser(userRequestDto));
    }
}
