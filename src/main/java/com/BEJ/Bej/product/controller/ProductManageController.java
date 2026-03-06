package com.BEJ.Bej.product.controller;

import com.BEJ.Bej.common.ApiResponse;
import com.BEJ.Bej.product.dto.request.ProductRequest;
import com.BEJ.Bej.product.dto.response.ProductListResponse;
import com.BEJ.Bej.product.dto.response.ProductResponse;
import com.BEJ.Bej.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/manage/product")
public class ProductManageController {

    ProductService productService;

    @GetMapping("/list")
    ApiResponse<List<ProductListResponse>> getAllProducts() {
//        ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
        return ApiResponse.<List<ProductListResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }
    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> getProductDetails(@PathVariable String productId){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductDetails(productId))
                .build();
    }
//
    @PutMapping("/update/{productId}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable String productId, @ModelAttribute ProductRequest request) throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productId, request))
                .build();
    }

//
    @PostMapping("/add")
    ApiResponse<ProductResponse> addNewProduct(@ModelAttribute @Valid ProductRequest request) throws IOException {
        System.out.println("product add");

        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.addNewProduct(request));

        return apiResponse;
    }

    @DeleteMapping("/delete/{productId}")
    ApiResponse<Void> deleteProduct(@PathVariable String productId){
        productService.delete(productId);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping("/inactive/{productId}")
    ApiResponse<Void> inactiveProduct(@PathVariable String productId){
        productService.inactive(productId);
        return ApiResponse.<Void>builder().build();
    }
}
