package com.BEJ.Bej.dto.response.cartResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {

    String id;

    String phoneNumber;
    String email;
    String address;
    LocalDate updatedAt;
    LocalDate orderAt;

    String description;

    List<OrderItemResponse> orderItems = new ArrayList<>();
}
