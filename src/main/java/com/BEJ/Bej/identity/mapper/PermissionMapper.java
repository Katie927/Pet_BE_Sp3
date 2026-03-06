package com.BEJ.Bej.identity.mapper;

import com.BEJ.Bej.identity.dto.request.PermissionRequest;
import com.BEJ.Bej.identity.dto.response.PermissionResponse;
import com.BEJ.Bej.identity.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
