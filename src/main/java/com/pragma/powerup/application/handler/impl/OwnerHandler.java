package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.handler.IOwnerHandler;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IOwnerUseCase;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.usecase.OwnerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerHandler implements IOwnerHandler {

    private final IOwnerUseCase ownerUseCase;
    private final UserRequestMapper userRequestMapper;

    @Override
    public void createEmployee(UserRequestDto userRequestDto, String ownerEmail) throws OwnerAlreadyExist {
        ownerUseCase.createEmployee(userRequestMapper.toUser(userRequestDto), ownerEmail);
    }

}
