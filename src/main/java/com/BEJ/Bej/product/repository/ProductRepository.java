package com.BEJ.Bej.product.repository;

import com.BEJ.Bej.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, String> {
    boolean existsByName(String name);
    List<Product> findAllByOrderByCreateDateDesc();
    List<Product> findByStatusOrderByCreateDateDesc(int status);

    Optional<Product> findByName(String productName);


    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId " +
            "AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND p.status = :status ORDER BY p.createDate DESC")
    List<Product> findByCategoryAndNameContainingIgnoreCaseAndStatus(
            @Param("categoryId") Long categoryId,
            @Param("name") String name,
            @Param("status") int status
    );

    List<Product> findByCategory_IdAndStatusOrderByCreateDateDesc(Long categoryId, int status);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.status = :status ORDER BY p.createDate DESC")
    List<Product> findByNameContainingIgnoreCaseAndStatus(@Param("name") String name, @Param("status") int status);
}
