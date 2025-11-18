package com.BEJ.Bej.controller.identity;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.identityRequest.UserCreationRequest;
import com.BEJ.Bej.dto.request.identityRequest.UserUpdateRequest;
import com.BEJ.Bej.dto.response.UserResponse;
import com.BEJ.Bej.dto.response.guest.GuestInfoResponse;
import com.BEJ.Bej.entity.identity.User;
import com.BEJ.Bej.service.identity.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping("/create")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

//    @GetMapping("/profile/{userId}")
//    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId){
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.getUser(userId))
//                .build();
//    }



    @GetMapping("/profile/my-info")
    ApiResponse<GuestInfoResponse> getMyInfo(){
        return ApiResponse.<GuestInfoResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/profile/my-info/update")
    ApiResponse<GuestInfoResponse> updateMyInfo(@RequestBody UserUpdateRequest request){
        return ApiResponse.<GuestInfoResponse>builder()
                .result(userService.updateMyInfo(request))
                .build();
    }
}
