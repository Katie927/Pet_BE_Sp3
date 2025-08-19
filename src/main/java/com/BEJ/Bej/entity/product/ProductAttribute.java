package com.BEJ.Bej.entity.product;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_attribute")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
//    String value;

//    int originalPrice;
//    int finalPrice;
//    int discount;
//
//    int stockQuantity;
//    int soldQuantity;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    ProductVariant variant;

//    @OneToMany(mappedBy = "variant_id", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<ProductVariant> values = new ArrayList<>();

}
