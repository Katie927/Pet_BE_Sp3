package com.BEJ.Bej.service;

import com.BEJ.Bej.dto.request.UserRequest.UserCreationRequest;
import com.BEJ.Bej.dto.request.UserRequest.UserUpdateRequest;
import com.BEJ.Bej.dto.response.UserResponse;
import com.BEJ.Bej.entity.User;
import com.BEJ.Bej.enums.Role;
import com.BEJ.Bej.exception.AppException;
import com.BEJ.Bej.exception.ErrorCode;
import com.BEJ.Bej.mapper.UserMapper;
import com.BEJ.Bej.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

// create User
    public UserResponse createUser(UserCreationRequest request){

        if (userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);         //su dung mapstruct
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }
// get all users
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }
    public UserResponse getUser(String userId){
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found!")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

//        System.out.println("Before update: " + user);
//        System.out.println("User update request: " + request);
        userMapper.updateUser(user, request);
//        System.out.println("After update: " + user);

        User savedUser = userRepository.save(user);
//        System.out.println("Saved User: " + savedUser);

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
