package com.BEJ.Bej.dto.response.guest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailRes {

    String name;
    String image;

    int status;
    LocalDate createDate;

    List<ProductImgRes> introImages;
    List<ProductVariantRes> variants;

}
