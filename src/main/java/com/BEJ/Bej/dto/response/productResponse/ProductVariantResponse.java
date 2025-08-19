package com.BEJ.Bej.dto.response.productResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantResponse {

    String id;
    String color;
//
    int originalPrice;
    int finalPrice;
    int discount;

    int stockQuantity;
    int soldQuantity;

    List<String> detailImages;
    List<ProductAttributeResponse> attributes;

}
