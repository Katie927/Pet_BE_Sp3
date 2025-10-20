package com.BEJ.Bej.dto.request.productRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAttributeRequest {

    String id;
    String name;
//    String value;

    double originalPrice;
    double finalPrice;
    double discount;

    int stockQuantity;
    int soldQuantity;

}
