package com.pragma.powerup.application.service;

import com.pragma.powerup.application.dto.request.AuthenticationRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.AuthenticationResponseDto;
import com.pragma.powerup.application.handler.impl.UserHandler;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.exception.AuthAttemptsException;
import com.pragma.powerup.infrastructure.exception.AuthFailedException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ConcurrentHashMap<String, Integer> attempts;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserEntityMapper userEntityMapper;
    private final UserHandler userHandler;

    public AuthenticationResponseDto register(UserRequestDto request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getLastName())
                .documentNumber(request.getDocumentNumber())
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .build();
        userHandler.createUser(request);
        userRepository.save(userEntityMapper.toEntity(user));
        var jwtToken = jwtService.generateToken(userEntityMapper.toEntity(user));
        return AuthenticationResponseDto.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto){
        String email = authenticationRequestDto.getEmail();
        if(!attempts.containsKey(email)){attempts.put(email, 0);}
        if(attempts.get(email) >= 2){
            throw new AuthAttemptsException();
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.getEmail(),
                            authenticationRequestDto.getPassword()
                    )
            );
        } catch (Exception e) {
            if (!attempts.containsKey(email)) {
                attempts.put(email, 0);
            }
            attempts.put(email, attempts.get(email) + 1);
            throw new AuthFailedException();
        }
        UserEntity user = userRepository.findByEmail(authenticationRequestDto.getEmail()).get();
        var jwtToken = jwtService.generateToken(user);
        attempts.put(email, 0);
        return AuthenticationResponseDto.builder()
                .accessToken(jwtToken)
                .build();
    }


}

