package com.BEJ.Bej.dto.request.cartRequest;

import com.BEJ.Bej.entity.cart.Orders;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequest {

    String productAttId;

    int quantity;
    double price;

}
