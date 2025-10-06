package com.BEJ.Bej.controller.identity;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.identityRequest.UserUpdateRequest;
import com.BEJ.Bej.dto.response.UserResponse;
import com.BEJ.Bej.entity.identity.User;
import com.BEJ.Bej.service.identity.UserManageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users/manage")
@Slf4j
public class ManageEmployeeController {

    UserManageService userManageService;

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable String userId){
        return ApiResponse.<UserResponse>builder()
                .result(userManageService.getUser(userId))
                .build();
    }

    @PutMapping("/update/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
//        System.out.println("Request Data: " + request);
        return ApiResponse.<UserResponse>builder()
                .result(userManageService.updateUser(userId, request))
                .build();
    }

}
