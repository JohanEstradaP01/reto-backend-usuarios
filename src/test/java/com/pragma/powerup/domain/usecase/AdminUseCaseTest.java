package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.service.IUserRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminUseCaseTest {

    private IUserRegistrationService userRegistrationService;
    private AdminUseCase adminUseCase;

    @BeforeEach
    void setUp() {
        userRegistrationService = mock(IUserRegistrationService.class);
        adminUseCase = new AdminUseCase(userRegistrationService);
    }

    @Test
    void shouldCreateOwnerSuccessfully() throws OwnerAlreadyExist {
        User user = User.builder()
                .name("Johan")
                .birthDate(LocalDate.now().minusYears(20))
                .build();

        assertDoesNotThrow(() -> adminUseCase.createOwner(user));

        // Verifica que se haya asignado el rol
        assertEquals(Role.OWNER, user.getRole());

        // Verifica que se haya llamado el servicio con los parámetros esperados
        verify(userRegistrationService, times(1)).registerUser(user, Role.OWNER);
    }

    @Test
    void shouldThrowUnderageOwnerException() throws OwnerAlreadyExist {
        User user = User.builder()
                .name("Juan")
                .birthDate(LocalDate.now().minusYears(17))
                .build();

        assertThrows(UnderageOwnerException.class, () -> adminUseCase.createOwner(user));

        // Verifica que no se haya llamado al servicio
        verify(userRegistrationService, never()).registerUser(any(), any());
    }

    @Test
    void shouldThrowOwnerAlreadyExistException() throws OwnerAlreadyExist {
        User user = User.builder()
                .name("Pedro")
                .birthDate(LocalDate.now().minusYears(30))
                .build();

        doThrow(new OwnerAlreadyExist()).when(userRegistrationService).registerUser(user, Role.OWNER);

        assertThrows(OwnerAlreadyExist.class, () -> adminUseCase.createOwner(user));

        verify(userRegistrationService, times(1)).registerUser(user, Role.OWNER);
    }

    @Test
    void shouldAssignOwnerRoleToUser() {
        User user = User.builder()
                .name("Lucía")
                .birthDate(LocalDate.now().minusYears(25))
                .role(Role.USER) // otro rol inicial
                .build();

        assertDoesNotThrow(() -> adminUseCase.createOwner(user));

        assertEquals(Role.OWNER, user.getRole());
    }
}
