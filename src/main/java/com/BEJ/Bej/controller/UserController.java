package com.BEJ.Bej.controller;

import com.BEJ.Bej.dto.request.UserCreationRequest;
import com.BEJ.Bej.entity.User;
import com.BEJ.Bej.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request);
    }

    @GetMapping
    List<User> getUser(){
        return userService.getUsers();
    }

    @GetMapping("/profile/{userId}")
    User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }
}
