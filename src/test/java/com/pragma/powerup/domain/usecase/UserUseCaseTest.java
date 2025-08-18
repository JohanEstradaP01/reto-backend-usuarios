package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.service.IUserRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    private IUserRegistrationService userRegistrationService;
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        userRegistrationService = mock(IUserRegistrationService.class);
        userUseCase = new UserUseCase(userRegistrationService);
    }

    @Test
    void getUser_success() {
        User expectedUser = User.builder()
                .id(1L)
                .name("John")
                .build();

        when(userRegistrationService.getUser(1L)).thenReturn(expectedUser);

        User result = userUseCase.getUser(1L);

        assertEquals(expectedUser, result);
        verify(userRegistrationService, times(1)).getUser(1L);
    }

    @Test
    void registerUser_success() throws OwnerAlreadyExist {
        User newUser = User.builder()
                .id(2L)
                .name("Jane")
                .build();

        userUseCase.registerUser(newUser);

        verify(userRegistrationService, times(1)).registerUser(newUser);
    }

    @Test
    void getUserByEmail_success() {
        User expectedUser = User.builder()
                .id(3L)
                .name("Alice")
                .build();

        when(userRegistrationService.getUserByEmail("alice@test.com")).thenReturn(expectedUser);

        User result = userUseCase.getUserByEmail("alice@test.com");

        assertEquals(expectedUser, result);
        verify(userRegistrationService, times(1)).getUserByEmail("alice@test.com");
    }
}
