package com.BEJ.Bej.identity.mapper;

import com.BEJ.Bej.identity.dto.request.RoleRequest;
import com.BEJ.Bej.identity.dto.response.RoleResponse;
import com.BEJ.Bej.identity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRResponse(Role permission);
}


