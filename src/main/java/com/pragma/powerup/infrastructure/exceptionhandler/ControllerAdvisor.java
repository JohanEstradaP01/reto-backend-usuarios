package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.*;
import com.pragma.powerup.infrastructure.exception.AuthAttemptsException;
import com.pragma.powerup.infrastructure.exception.AuthFailedException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put(MESSAGE, message);
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoDataFoundException(NoDataFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ExceptionResponse.NO_DATA_FOUND.getMessage());
    }

    @ExceptionHandler(UnderageOwnerException.class)
    public ResponseEntity<Map<String, Object>> handleUnderageOwnerException(UnderageOwnerException ex) {
        return buildErrorResponse(BAD_REQUEST, "El usuario debe ser mayor de edad");
    }

    @ExceptionHandler(OwnerAlreadyExist.class)
    public ResponseEntity<Map<String, Object>> ownerAlreadyExistException(OwnerAlreadyExist ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "El propietario ya se encuentra registrado");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errores.put(fe.getField(), fe.getDefaultMessage());
        }
        Map<String, Object> body = new HashMap<>();
        body.put("status", BAD_REQUEST.value());
        body.put("error", BAD_REQUEST.getReasonPhrase());
        body.put("errors", errores);
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(EmailFormatNoValid.class)
    public ResponseEntity<Map<String, Object>> emailValidationError(EmailFormatNoValid ex) {
        return buildErrorResponse(BAD_REQUEST, "Formato de email no válido");
    }

    @ExceptionHandler(PhoneNumberFormatNoValid.class)
    public ResponseEntity<Map<String, Object>> phoneNumberNoValid(PhoneNumberFormatNoValid ex) {
        return buildErrorResponse(BAD_REQUEST, "Formato de teléfono no válido");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> incompleteFields(NullPointerException ex) {
        return buildErrorResponse(BAD_REQUEST, "Los campos requeridos no se enviaron correctamente.");
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String, Object>> userNotFound(UserNotFound ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "El usuario no fue encontrado");
    }

    @ExceptionHandler(AuthAttemptsException.class)
    public ResponseEntity<Map<String, Object>> authAttemptsException(AuthAttemptsException ex) {
        return buildErrorResponse(BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AuthFailedException.class)
    public ResponseEntity<Map<String, Object>> authFailedException(AuthFailedException ex){
        return buildErrorResponse(BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<Map<String, Object>> userAlreadyExist(UserAlreadyExist ex) {
        return buildErrorResponse(CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(UserNotIsOwnerException.class)
    public ResponseEntity<Map<String, Object>> userNotIsOwnerException(UserNotIsOwnerException ex) {
        return buildErrorResponse(CONFLICT, ex.getMessage());
    }

}
