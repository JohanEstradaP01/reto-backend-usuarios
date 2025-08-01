package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.handler.impl.AdminHandler;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.exception.EmailFormatNoValid;
import com.pragma.powerup.domain.exception.OwnerAlreadyExist;
import com.pragma.powerup.domain.exception.PhoneNumberFormatNoValid;
import com.pragma.powerup.domain.exception.UnderageOwnerException;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminHandlerTest {

    @Mock
    private IAdminServicePort adminUseCase;

    @Mock
    private UserRequestMapper userRequestMapper;

    @InjectMocks
    private AdminHandler adminHandler;

    private UserRequestDto userRequestDto;
    private User user;

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();

        user = User.builder()
                .id(1L)
                .name("Juan")
                .lastName("Pérez")
                .documentNumber("123456789")
                .phone("+573001112233")
                .birthDate(LocalDate.of(2000, 1, 1))
                .email("juan.perez@example.com")
                .password("securePass123")
                .role(Role.USER)
                .build();
    }

    @Test
    void createOwner_ShouldCallUseCase_WhenValidInput() throws Exception {
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(user);

        adminHandler.createOwner(userRequestDto);

        verify(userRequestMapper).toUser(userRequestDto);
        verify(adminUseCase).createOwner(user);
    }

    @Test
    void createOwner_ShouldPropagate_UnderageOwnerException() throws Exception {
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(user);
        doThrow(new UnderageOwnerException()).when(adminUseCase).createOwner(user);

        assertThrows(
                UnderageOwnerException.class,
                () -> adminHandler.createOwner(userRequestDto)
        );
    }

    @Test
    void createOwner_ShouldPropagate_EmailFormatNoValid() throws Exception {
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(user);
        doThrow(new EmailFormatNoValid()).when(adminUseCase).createOwner(user);

        assertThrows(
                EmailFormatNoValid.class,
                () -> adminHandler.createOwner(userRequestDto)
        );
    }

    @Test
    void createOwner_ShouldPropagate_PhoneNumberFormatNoValid() throws Exception {
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(user);
        doThrow(new PhoneNumberFormatNoValid()).when(adminUseCase).createOwner(user);

        assertThrows(
                PhoneNumberFormatNoValid.class,
                () -> adminHandler.createOwner(userRequestDto)
        );
    }

    @Test
    void createOwner_ShouldPropagate_OwnerAlreadyExist() throws Exception {
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(user);
        doThrow(new OwnerAlreadyExist()).when(adminUseCase).createOwner(user);

        assertThrows(
                OwnerAlreadyExist.class,
                () -> adminHandler.createOwner(userRequestDto)
        );
    }
}
