package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.identityRequest.RoleRequest;
import com.BEJ.Bej.dto.response.identity.RoleResponse;
import com.BEJ.Bej.entity.identity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRResponse(Role permission);
}


