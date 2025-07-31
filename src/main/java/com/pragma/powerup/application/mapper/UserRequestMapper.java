package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserRequestMapper {

    User toUser(UserRequestDto userRequestDto);

    default Role mapRole(String roleName) {
        if (roleName == null || roleName.isBlank()) {
            return null;
        }
        return Role.valueOf(roleName.toUpperCase());
    }

}
