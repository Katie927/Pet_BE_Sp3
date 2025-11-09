package com.BEJ.Bej.repository.product;

import com.BEJ.Bej.entity.cart.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
}
