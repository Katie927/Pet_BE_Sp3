package com.BEJ.Bej.identity.mapper;

import com.BEJ.Bej.identity.dto.request.UserCreationRequest;
import com.BEJ.Bej.identity.dto.request.UserUpdateRequest;
import com.BEJ.Bej.identity.dto.response.UserResponse;
import com.BEJ.Bej.cart.dto.response.guest.GuestInfoResponse;
import com.BEJ.Bej.identity.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    GuestInfoResponse toGuestInfoResponse(User user);

    @Mapping(target = "roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
