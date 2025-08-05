package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IAUserHandler;
import com.pragma.powerup.application.handler.IAdminHandler;
import com.pragma.powerup.domain.exception.EmailFormatNoValid;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.PhoneNumberFormatNoValid;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final IAdminHandler adminHandler;
    private final IAUserHandler userHandler;

    @Operation(summary = "Create an owner")
    @PostMapping("/owner")
    public ResponseEntity<Void> createOwner(@RequestBody UserRequestDto userRequestDto)
            throws UnderageOwnerException, OwnerAlreadyExist,
            EmailFormatNoValid, PhoneNumberFormatNoValid {
        adminHandler.createOwner(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userHandler.geUser(id));
    }

}
