package com.BEJ.Bej.cart.dto.request.cartRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatusRequest {

    int status;
    String description;

    List<OrderItemRequest> items;
    int quantity;

}
