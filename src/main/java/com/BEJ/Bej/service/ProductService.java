package com.BEJ.Bej.service;

import com.BEJ.Bej.dto.request.productRequest.ProductRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.entity.product.Product;
import com.BEJ.Bej.entity.product.ProductAttribute;
import com.BEJ.Bej.exception.AppException;
import com.BEJ.Bej.exception.ErrorCode;
import com.BEJ.Bej.mapper.ProductMapper;
import com.BEJ.Bej.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

//    @PreAuthorize((has))
    public List<ProductResponse> getProducts(){
        return productRepository.findByStatusOrderByCreateDateDesc(1).stream().map(productMapper::toProductResponse).toList();
    }
    // admin get
    public List<ProductResponse> getAllProducts(){
        return productRepository.findAllByOrderByCreateDateDesc().stream().map(productMapper::toProductResponse).toList();
    }

    public ProductResponse addNewProduct(ProductRequest request){
        if(productRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        Product product = productMapper.toProduct(request);
        product.setCreateDate(LocalDate.now());
        System.out.println(product.getName());

        List<ProductAttribute> attributes = Optional.ofNullable(request.getAttributes())
                .orElse(Collections.emptyList())
                .stream()
                .map(attr -> {
                    ProductAttribute attribute = new ProductAttribute();
                    attribute.setValue(attr);
                    attribute.setProduct(product);
                    return attribute;
                })
                .toList();
        product.setAttributes(attributes);
        System.out.println(product.getAttributes());

        return productMapper.toProductResponse(productRepository.save(product));
    }

}
