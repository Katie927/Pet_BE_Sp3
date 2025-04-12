package com.BEJ.Bej.controller.manage;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.productRequest.ProductRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin/product")
public class ProductManageController {

    ProductService productService;

    @PostMapping("/add")
    ApiResponse<ProductResponse> addNewProduct(@RequestBody @Valid ProductRequest request){
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.addNewProduct(request));

        return apiResponse;
    }
}
