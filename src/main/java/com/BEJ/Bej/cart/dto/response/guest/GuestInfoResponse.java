package com.BEJ.Bej.cart.dto.response.guest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuestInfoResponse {
    String id;
    String fullName;
    String address;
    LocalDate dob;
    String email;
    String phoneNumber;
}
