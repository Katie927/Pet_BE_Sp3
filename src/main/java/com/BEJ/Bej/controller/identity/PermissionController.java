package com.BEJ.Bej.controller.identity;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.identityRequest.PermissionRequest;
import com.BEJ.Bej.dto.response.identity.PermissionResponse;
import com.BEJ.Bej.service.identity.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permissions")
@Slf4j
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("{permission}")
    public ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }

}
