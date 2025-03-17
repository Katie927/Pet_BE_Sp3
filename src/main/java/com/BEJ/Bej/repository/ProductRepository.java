package com.BEJ.Bej.repository;

import com.BEJ.Bej.dto.response.ProductResponse;
import com.BEJ.Bej.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, String> {
    List<Product> findAllByOrderByCreateDateDesc();
}
