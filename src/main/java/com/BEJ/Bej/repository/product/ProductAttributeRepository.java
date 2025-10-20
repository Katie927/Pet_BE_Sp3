package com.BEJ.Bej.repository.product;

import com.BEJ.Bej.entity.product.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, String> {
}
