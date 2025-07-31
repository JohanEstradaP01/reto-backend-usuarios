package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.handler.IAdminHandler;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.usecase.AdminUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminHandler implements IAdminHandler {

    private final IAdminServicePort adminUseCase;
    private final UserRequestMapper userRequestMapper;

    @Override
    public void createOwner(UserRequestDto userRequestDto) throws UnderageOwnerException, OwnerAlreadyExist {
        User user = userRequestMapper.toUser(userRequestDto);
        adminUseCase.createOwner(user);
    }

}
