package com.BEJ.Bej.cart.repository;

import com.BEJ.Bej.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    List<CartItem> findAllByUserId(String userId);
    CartItem findByUser_IdAndProductA_Id(String userId, String attributeId);

}
