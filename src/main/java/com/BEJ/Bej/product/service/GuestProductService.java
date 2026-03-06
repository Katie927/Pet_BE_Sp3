package com.BEJ.Bej.product.service;

import com.BEJ.Bej.cart.dto.response.guest.ProductDetailRes;
import com.BEJ.Bej.common.exception.AppException;
import com.BEJ.Bej.common.exception.ErrorCode;
import com.BEJ.Bej.product.mapper.ProductMapper;
import com.BEJ.Bej.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class GuestProductService {

    ProductMapper productMapper;
    ProductRepository productRepository;

    public ProductDetailRes getProductDetails(String productId){
        return productMapper.toProductDetailResponse(productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

}
