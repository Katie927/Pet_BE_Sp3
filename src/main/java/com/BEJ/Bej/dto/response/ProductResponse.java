package com.BEJ.Bej.dto.response;

import com.BEJ.Bej.entity.product.ProductAttribute;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String name;
    String image;
    int originalPrice;
    int discount;
    int finalPrice;
    private List<String> specs;
}
