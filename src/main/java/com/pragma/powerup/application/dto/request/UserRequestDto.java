package com.pragma.powerup.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "El valor debe ser numérico")
    private String documentNumber;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Teléfono no válido")
    private String phone;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, message = "La longitud debe ser de minimo 8 caracteres")
    private String password;

    private String role;

}
