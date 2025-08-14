package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IAUserHandler;
import com.pragma.powerup.application.handler.IAdminHandler;
import com.pragma.powerup.application.handler.IOwnerHandler;
import com.pragma.powerup.application.service.JwtService;
import com.pragma.powerup.domain.exception.EmailFormatNoValid;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.PhoneNumberFormatNoValid;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final IAdminHandler adminHandler;
    private final IAUserHandler userHandler;
    private final IOwnerHandler ownerHandler;
    private final JwtService jwtService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/owner")
    public ResponseEntity<Void> createOwner(@Valid @RequestBody UserRequestDto userRequestDto)
            throws UnderageOwnerException, OwnerAlreadyExist,
            EmailFormatNoValid, PhoneNumberFormatNoValid {
        adminHandler.createOwner(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userHandler.geUser(id));
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/employee")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody UserRequestDto userRequestDto, @RequestHeader("Authorization") String authorization) throws OwnerAlreadyExist {
        String token = authorization.substring(7);
        System.out.println(token);
        String email = jwtService.extractUsername(token);
        ownerHandler.createEmployee(userRequestDto, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USER','EMPLOYEE')")
    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByToken(@RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        System.out.println(token);
        String email = jwtService.extractUsername(token);
        return ResponseEntity.ok(userHandler.getUserByEmail(email));
    }

}
