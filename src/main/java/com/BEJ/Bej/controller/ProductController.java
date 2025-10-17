package com.BEJ.Bej.controller;


import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.response.guest.ProductDetailResponse;
import com.BEJ.Bej.dto.response.productResponse.ProductListResponse;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.service.ProductService;
import com.BEJ.Bej.service.guest.GuestProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/")
public class ProductController {

    ProductService productService;
    GuestProductService guestProductService;

    @GetMapping
    ApiResponse<List<ProductListResponse>> getProducts() {
//        ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
        return ApiResponse.<List<ProductListResponse>>builder()
                .result(productService.getProducts())
                .build();
    }

    @GetMapping("/product/{productId}")
    ApiResponse<ProductDetailResponse> getProductDetails(@PathVariable String productId){
        return ApiResponse.<ProductDetailResponse>builder()
                .result(guestProductService.getProductDetails(productId))
                .build();
    }

//    @PostMapping()
}
