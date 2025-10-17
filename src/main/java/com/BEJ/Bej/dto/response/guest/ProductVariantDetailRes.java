package com.BEJ.Bej.dto.response.guest;

import com.BEJ.Bej.dto.response.productResponse.ProductAttributeResponse;
import com.BEJ.Bej.dto.response.productResponse.ProductImageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantDetailRes {

    String color;

    List<ProductImageResponse> detailImages;
    List<ProductAttRes> attributes;

}
