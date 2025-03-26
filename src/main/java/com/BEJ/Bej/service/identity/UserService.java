package com.BEJ.Bej.service.identity;

import com.BEJ.Bej.dto.request.identityRequest.UserCreationRequest;
import com.BEJ.Bej.dto.request.identityRequest.UserUpdateRequest;
import com.BEJ.Bej.dto.response.UserResponse;
import com.BEJ.Bej.entity.identity.User;
import com.BEJ.Bej.enums.Role;
import com.BEJ.Bej.exception.AppException;
import com.BEJ.Bej.exception.ErrorCode;
import com.BEJ.Bej.mapper.UserMapper;
import com.BEJ.Bej.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
//        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }
// get all users
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        log.info("in method GET /users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String userId){
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found!")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        userMapper.updateUser(user, request);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(userRepository.save(user));
    }

//    public UserResponse updateMyInfo(UserUpdateRequest request){
//        var context = SecurityContextHolder.getContext();
//        String name = context.getAuthentication().getName();
//        User user = userRepository.findByEmail(name).orElseThrow(
//                () -> new AppException(ErrorCode.USER_NOT_EXISTED));;
//        userMapper.updateUser();
//    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }
}
