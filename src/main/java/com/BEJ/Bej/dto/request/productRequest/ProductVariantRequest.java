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

    String id;
    String color;

    List<ProductImageRequest> detailImages;
    List<ProductAttributeRequest> attributes;
}
