package com.BEJ.Bej.dto.response.guest;

import com.BEJ.Bej.dto.response.productResponse.ProductImageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {

    String name;
    String image;

    int status;
    LocalDate createDate;

    List<ProductImageResponse> introImages;
    List<ProductVariantDetailRes> variants;

}
