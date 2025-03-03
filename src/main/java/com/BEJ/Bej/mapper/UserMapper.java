package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.UserCreationRequest;
import com.BEJ.Bej.dto.request.UserUpdateRequest;
import com.BEJ.Bej.dto.response.UserResponse;
import com.BEJ.Bej.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

//    @Mapping(target = "fullName", ignore = false)
//    @Mapping(target = "address", ignore = false)
//    @Mapping(target = "dob", ignore = false)
//    @Mapping(target = "phoneNumber", ignore = false)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
