package com.BEJ.Bej.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String fullName;
//    String password;
    String address;
    LocalDate dob;
    String email;
    String phoneNumber;
    Set<String> roles;
}
