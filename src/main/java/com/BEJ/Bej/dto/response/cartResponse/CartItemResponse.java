package com.BEJ.Bej.dto.response.cartResponse;

import com.BEJ.Bej.entity.product.ProductAttribute;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {

    String img;

    String productAttName;
    int quantity;
    double price;
    String color;
    String productName;

}
