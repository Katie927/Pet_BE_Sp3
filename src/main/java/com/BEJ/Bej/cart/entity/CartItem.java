package com.BEJ.Bej.cart.entity;

import com.BEJ.Bej.identity.entity.User;
import com.BEJ.Bej.product.entity.ProductAttribute;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "attribute_id")
    ProductAttribute productA;
    int quantity;
    @Column(nullable = false)
    double price;
    LocalDate addedAt;

    String color;
    String productName;

}
