package com.pragma.powerup.infrastructure.exception;

public class AuthAttemptsException extends RuntimeException {
    public AuthAttemptsException() {
        super("Superó los intentos permitidos de inicio de sesión");
    }
}
