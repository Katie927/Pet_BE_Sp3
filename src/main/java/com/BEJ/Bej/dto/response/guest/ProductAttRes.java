package com.BEJ.Bej.dto.response.guest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAttRes {
    String id;
    String name;

    int originalPrice;
    int finalPrice;
    int discount;
}
