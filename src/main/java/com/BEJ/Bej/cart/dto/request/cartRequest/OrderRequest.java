package com.BEJ.Bej.cart.dto.request.cartRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

    List<OrderItemRequest> items;

    int type;

}
