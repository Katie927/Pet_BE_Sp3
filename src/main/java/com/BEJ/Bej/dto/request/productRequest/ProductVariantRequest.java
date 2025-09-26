package com.BEJ.Bej.dto.request.productRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantRequest {

    String color;

    int originalPrice;
    int finalPrice;
    int discount;

    int stockQty = 0;
    int soldQty = 0;

    List<ProductImageRequest> detailImages;

    List<ProductAttributeRequest> attributes;
}
