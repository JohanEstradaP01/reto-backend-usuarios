package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.*;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(UnderageOwnerException.class)
    public ResponseEntity<String> handleUnderageOwnerException (UnderageOwnerException ex) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body("El usuario debe ser mayor de edad");
    }

    @ExceptionHandler(OwnerAlreadyExist.class)
    public ResponseEntity<String> ownerAlreadyExistException (OwnerAlreadyExist ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("El propieatrio ya se encuentra registrado");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errores.put(fe.getField(), fe.getDefaultMessage());
        }
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(errores);
    }

    @ExceptionHandler(EmailFormatNoValid.class)
    public ResponseEntity<String> emailValidationError(EmailFormatNoValid ex){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body("Formato de email no válido");
    }

    @ExceptionHandler(PhoneNumberFormatNoValid.class)
    public ResponseEntity<String> phoneNumberNoValid(PhoneNumberFormatNoValid ex){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body("Formato de teléfono no valido");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> incompleteFields(NullPointerException ex){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body("Los campos requeridos no se enviaron correctamente.");
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> userNotFound(UserNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("El usuario no fue encontrado");
    }

}