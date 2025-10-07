package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.identityRequest.UserCreationRequest;
import com.BEJ.Bej.dto.request.identityRequest.UserUpdateRequest;
import com.BEJ.Bej.dto.response.UserResponse;
import com.BEJ.Bej.entity.identity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
