package com.BEJ.Bej.repository;

import com.BEJ.Bej.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, String> {
    boolean existsByName(String name);
    List<Product> findAllByOrderByCreateDateDesc();
    List<Product> findByStatusOrderByCreateDateDesc(int status);
}
