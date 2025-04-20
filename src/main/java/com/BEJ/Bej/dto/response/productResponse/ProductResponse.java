package com.BEJ.Bej.dto.response.productResponse;

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
    List<String> detailImages;
    int originalPrice;
    int discount;
    int finalPrice;
    List<String> attributes;
    int status;
    LocalDate createDate;
}
