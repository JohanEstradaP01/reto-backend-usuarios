package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.exception.EmailFormatNoValid;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.PhoneNumberFormatNoValid;
import com.pragma.powerup.domain.exception.UnderageOwnerException;

public interface IAdminHandler {

    void createOwner(UserRequestDto userRequestDto) throws UnderageOwnerException, OwnerAlreadyExist, EmailFormatNoValid, PhoneNumberFormatNoValid;

}
