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

    public ProductResponse addNewProduct(ProductRequest request){
        if(productRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        Product product = productMapper.toProduct(request);
        product.setCreateDate(LocalDate.now());

        List<ProductAttribute> attributes = request.getAttributes().stream()
                .map(attr -> {
                    ProductAttribute attribute = new ProductAttribute();
                    attribute.setName(attr);
                    attribute.setProduct(product);  // set FK nếu cần
                    return attribute;
                })
                .toList();
        product.setAttributes(attributes);

        return productMapper.toProductResponse(productRepository.save(product));
    }

}
