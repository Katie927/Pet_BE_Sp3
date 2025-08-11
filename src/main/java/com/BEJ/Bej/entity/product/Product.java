package com.BEJ.Bej.entity.product;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    String name;
    String description;
    LocalDate createDate;

    String image;
    int status; // 0: ngừng kinh doanh, 1: đang bán

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductVariant> variants = new ArrayList<>();
}
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    String id;
//    String name;
//    String image;
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<ProductImage> detailImages;
//
//    int originalPrice;
//    int discount;
//    int finalPrice;
//
//    String color;
//
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductAttribute> attributes = new ArrayList<>();
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "category_id", nullable = false)
////    Category category;
////
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "brand_id", nullable = false)
////    Brand brand;
//
//    int status = 1;
//    int stockQuantity;
//    int soldQuantity;
//
//    LocalDate createDate;
//
//}

// fix entity chia mau sac va cac phien ban bo cau hinh