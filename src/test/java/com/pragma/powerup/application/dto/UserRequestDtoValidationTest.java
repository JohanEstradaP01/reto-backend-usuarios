package com.pragma.powerup.application.dto;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestDtoValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private UserRequestDto buildValidDto() {
        UserRequestDto dto = new UserRequestDto();
        dto.setName("Juan");
        dto.setLastName("Pérez");
        dto.setDocumentNumber("123456789");
        dto.setPhone("+573001234567");
        dto.setBirthDate(LocalDate.of(2000, 1, 1));
        dto.setEmail("juan.perez@example.com");
        dto.setPassword("contrasena123");
        dto.setRole("ADMIN");
        return dto;
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        UserRequestDto dto = buildValidDto();
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "No debería haber errores de validación");
    }

    @Test
    void whenPhoneTooLong_thenValidationFails() {
        UserRequestDto dto = buildValidDto();
        dto.setPhone("+57300123456789"); // 14 dígitos

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phone")));
    }

    @Test
    void whenPhoneMissingPlus_thenValidationFails() {
        UserRequestDto dto = buildValidDto();
        dto.setPhone("300123456789");
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phone")));
    }

    @Test
    void whenBirthDateInFuture_thenValidationFails() {
        UserRequestDto dto = buildValidDto();
        dto.setBirthDate(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("birthDate")));
    }

    @Test
    void whenEmailInvalid_thenValidationFails() {
        UserRequestDto dto = buildValidDto();
        dto.setEmail("correo-invalido");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void whenPasswordTooShort_thenValidationFails() {
        UserRequestDto dto = buildValidDto();
        dto.setPassword("1234");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }
}
