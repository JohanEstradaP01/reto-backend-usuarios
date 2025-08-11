package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = false)
)
public interface UserResponseMapper {

    public UserResponseDto toUSerResponse(User user);

    public User toUser(UserRequestDto userRequestDto);

}
