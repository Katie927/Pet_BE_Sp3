package com.BEJ.Bej.dto.request.cartRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    String customerName;

    String phoneNumber;
    String email;
    String address;

    String description;

//    double totalPrice;
    List<OrderItemRequest> items;

    int type;

}
