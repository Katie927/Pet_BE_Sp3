package com.BEJ.Bej.controller.manage;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.productRequest.ProductRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin/product")
public class ProductManageController {

    ProductService productService;

    @GetMapping("/list")
    ApiResponse<List<ProductResponse>> getAllProducts() {
//        ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @PostMapping("/add")
    ApiResponse<ProductResponse> addNewProduct(@RequestBody @Valid ProductRequest request){
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.addNewProduct(request));

        return apiResponse;
    }

    @DeleteMapping("/delete/{productId}")
    ApiResponse<Void> deleteProduct(@PathVariable String productId){
        productService.delete(productId);
        return ApiResponse.<Void>builder().build();
    }
}
