package com.BEJ.Bej.cart.dto.response.guest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantRes {

    String color;

    List<ProductImgRes> detailImages;
    List<ProductAttRes> attributes;

}
