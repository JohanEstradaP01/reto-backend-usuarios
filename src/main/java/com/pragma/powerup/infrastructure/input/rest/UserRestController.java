package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.handler.IAdminHandler;
import com.pragma.powerup.domain.exception.EmailFormatNoValid;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.PhoneNumberFormatNoValid;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final IAdminHandler adminHandler;

    @Operation(summary = "Create an owner")
    @PostMapping("/owner")
    public ResponseEntity<Void> createOwner(@Valid @RequestBody UserRequestDto userRequestDto)
            throws UnderageOwnerException, OwnerAlreadyExist,
            EmailFormatNoValid, PhoneNumberFormatNoValid {
        adminHandler.createOwner(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
