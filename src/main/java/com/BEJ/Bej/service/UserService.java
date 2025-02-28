package com.BEJ.Bej.service;

import com.BEJ.Bej.dto.request.UserCreationRequest;
import com.BEJ.Bej.entity.User;
import com.BEJ.Bej.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

// create User
    public User createUser(UserCreationRequest request){
        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }
// get all users
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUser(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
    }
}
