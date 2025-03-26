package com.BEJ.Bej.service.identity;

import com.BEJ.Bej.dto.request.identityRequest.PermissionRequest;
import com.BEJ.Bej.dto.response.identity.PermissionResponse;
import com.BEJ.Bej.entity.identity.Permission;
import com.BEJ.Bej.mapper.PermissionMapper;
import com.BEJ.Bej.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }

}
