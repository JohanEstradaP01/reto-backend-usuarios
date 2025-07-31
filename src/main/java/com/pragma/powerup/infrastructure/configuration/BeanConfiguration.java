package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.service.IUserRegistrationService;
import com.pragma.powerup.domain.service.UserRegisterService;
import com.pragma.powerup.domain.spi.IPasswordEncryptor;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.AdminUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IObjectRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.encoder.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public IUserRegistrationService userRegistrationService(){
        return new UserRegisterService(userPersistencePort(), passwordEncryptor());
    }

}