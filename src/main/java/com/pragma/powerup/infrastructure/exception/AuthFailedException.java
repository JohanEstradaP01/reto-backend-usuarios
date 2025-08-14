package com.pragma.powerup.infrastructure.exception;

public class AuthFailedException extends RuntimeException {
    public AuthFailedException() {
        super("Autenticación fallida");
    }
}
