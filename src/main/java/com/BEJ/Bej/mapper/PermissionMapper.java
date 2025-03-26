package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.identityRequest.PermissionRequest;
import com.BEJ.Bej.dto.response.identity.PermissionResponse;
import com.BEJ.Bej.entity.identity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
