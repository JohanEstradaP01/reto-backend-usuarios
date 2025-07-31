package com.pragma.powerup.infrastructure.encoder;

import com.pragma.powerup.domain.spi.IPasswordEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor implements IPasswordEncryptor {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

}
