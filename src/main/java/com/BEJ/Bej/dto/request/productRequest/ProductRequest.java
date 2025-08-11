package com.BEJ.Bej.dto.request.productRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    String name;
    String description;

    MultipartFile image;

    int status;

    List<ProductVariantRequest> variants;

//    List<String> attributes;
}
