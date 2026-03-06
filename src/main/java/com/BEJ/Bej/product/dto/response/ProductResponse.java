package com.BEJ.Bej.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    String image;

    int status;
    LocalDate createDate;

    List<ProductImageResponse> introImages;
    List<ProductVariantResponse> variants;

    CategoryResponse category;
}
