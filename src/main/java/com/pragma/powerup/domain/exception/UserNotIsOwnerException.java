package com.pragma.powerup.domain.exception;

public class UserNotIsOwnerException extends RuntimeException {
    public UserNotIsOwnerException() {
        super("El usuario indicado no es propietario");
    }
}
