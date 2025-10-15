package com.BEJ.Bej.dto.response.productResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VariantSummaryResponse {

    int originalPrice;
    int finalPrice;

}
