package com.BEJ.Bej.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductListResponse {

    String id;
    String name;
    String image;

    int status;
    LocalDate createDate;

    VariantSummaryResponse variant;
}
