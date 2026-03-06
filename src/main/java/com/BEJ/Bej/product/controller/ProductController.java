package com.BEJ.Bej.product.controller;


import com.BEJ.Bej.common.ApiResponse;
import com.BEJ.Bej.cart.dto.response.guest.ProductDetailRes;
import com.BEJ.Bej.product.dto.response.ProductListResponse;
import com.BEJ.Bej.product.service.ProductService;
import com.BEJ.Bej.product.service.GuestProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/home")
public class ProductController {

    ProductService productService;
    GuestProductService guestProductService;

    @GetMapping
    ApiResponse<List<ProductListResponse>> getProducts() {
        return ApiResponse.<List<ProductListResponse>>builder()
                .result(productService.getProducts())
                .build();
    }

    @GetMapping("/product/{productId}")
    ApiResponse<ProductDetailRes> getProductDetails(@PathVariable String productId){
        return ApiResponse.<ProductDetailRes>builder()
                .result(guestProductService.getProductDetails(productId))
                .build();
    }

//    @PostMapping()

    //search
    @GetMapping("/products/search")
    ApiResponse<List<ProductListResponse>> searchProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String name) {
        return ApiResponse.<List<ProductListResponse>>builder()
                .result(productService.searchProducts(categoryId, name))
                .build();
    }
}
