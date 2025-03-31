package com.BEJ.Bej.service;

import com.BEJ.Bej.dto.response.ProductResponse;
import com.BEJ.Bej.mapper.ProductMapper;
import com.BEJ.Bej.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

//    @PreAuthorize((has))
    public List<ProductResponse> getProducts(){
        return productRepository.findAllByOrderByCreateDateDesc().stream().map(productMapper::toProductResponse).toList();
    }

}
