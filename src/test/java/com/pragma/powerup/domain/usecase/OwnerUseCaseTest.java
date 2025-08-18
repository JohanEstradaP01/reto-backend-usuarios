package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.UserNotIsOwnerException;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.service.IUserRegistrationService;
import com.pragma.powerup.domain.spi.IRestaurantConsultPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OwnerUseCaseTest {

    private IUserRegistrationService userRegistrationService;
    private IRestaurantConsultPort restaurantConsultPort;
    private OwnerUseCase ownerUseCase;

    @BeforeEach
    void setUp() {
        userRegistrationService = mock(IUserRegistrationService.class);
        restaurantConsultPort = mock(IRestaurantConsultPort.class);
        ownerUseCase = new OwnerUseCase(userRegistrationService, restaurantConsultPort);
    }

    @Test
    void createEmployee_success() throws OwnerAlreadyExist {
        User owner = User.builder()
                .role(Role.OWNER)
                .documentNumber("123")
                .build();

        User employee = User.builder()
                .restaurantId(1L)
                .build();

        when(userRegistrationService.getUserByEmail("owner@test.com")).thenReturn(owner);
        when(restaurantConsultPort.consultRestaurantOwner(1L)).thenReturn(123L);

        ownerUseCase.createEmployee(employee, "owner@test.com");

        verify(userRegistrationService, times(1)).registerUser(employee, Role.EMPLOYEE);
    }

    @Test
    void createEmployee_notOwner() {
        User owner = User.builder()
                .role(Role.EMPLOYEE)
                .documentNumber("123")
                .build();

        User employee = User.builder()
                .restaurantId(1L)
                .build();

        when(userRegistrationService.getUserByEmail("owner@test.com")).thenReturn(owner);
        when(restaurantConsultPort.consultRestaurantOwner(1L)).thenReturn(123L);

        assertThrows(UserNotIsOwnerException.class, () ->
                ownerUseCase.createEmployee(employee, "owner@test.com")
        );
    }

    @Test
    void createEmployee_documentMismatch() {
        User owner = User.builder()
                .role(Role.OWNER)
                .documentNumber("999")
                .build();

        User employee = User.builder()
                .restaurantId(1L)
                .build();

        when(userRegistrationService.getUserByEmail("owner@test.com")).thenReturn(owner);
        when(restaurantConsultPort.consultRestaurantOwner(1L)).thenReturn(123L);

        assertThrows(UserNotIsOwnerException.class, () ->
                ownerUseCase.createEmployee(employee, "owner@test.com")
        );
    }
}
