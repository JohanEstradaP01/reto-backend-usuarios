package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = false)
)
public interface UserRequestMapper {

    default User toUser(UserRequestDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .documentNumber(dto.getDocumentNumber())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(mapRole(dto.getRole()))
                .build();
    }


    default Role mapRole(String roleName) {
        if (roleName == null || roleName.isBlank()) {
            return null;
        }
        return Role.valueOf(roleName.toUpperCase());
    }

}
