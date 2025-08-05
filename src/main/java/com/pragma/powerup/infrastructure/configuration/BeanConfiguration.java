package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.api.IOwnerUseCase;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.service.IUserRegistrationService;
import com.pragma.powerup.domain.service.UserRegisterService;
import com.pragma.powerup.domain.spi.IPasswordEncryptor;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.AdminUseCase;
import com.pragma.powerup.domain.usecase.OwnerUseCase;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IObjectRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.encoder.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;
    private final UserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;


    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean IPasswordEncryptor passwordEncryptor() {
        return new PasswordEncryptor();
    }

    @Bean
    public IAdminServicePort adminServicePort() {
        return new AdminUseCase(userRegistrationService());
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userRegistrationService());
    }

    @Bean
    public IUserRegistrationService userRegistrationService(){
        return new UserRegisterService(userPersistencePort(), passwordEncryptor());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).get();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IOwnerUseCase ownerUseCase(){
        return new OwnerUseCase(userRegistrationService());
    }

}