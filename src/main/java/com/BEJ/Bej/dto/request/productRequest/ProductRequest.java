package com.BEJ.Bej.dto.request.productRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    String name;
    int originalPrice;
    int discount;
    int status;
    int stockQuantity;
    String color;

    MultipartFile image;
    List<MultipartFile> detailImages;

    List<String> attributes;
}
