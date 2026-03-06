package com.BEJ.Bej.identity.controller;

import com.BEJ.Bej.common.ApiResponse;
import com.BEJ.Bej.identity.dto.request.UserCreationRequest;
import com.BEJ.Bej.identity.dto.request.UserUpdateRequest;
import com.BEJ.Bej.identity.dto.response.UserResponse;
import com.BEJ.Bej.cart.dto.response.guest.GuestInfoResponse;
import com.BEJ.Bej.identity.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
