package com.BEJ.Bej.product.controller;

import com.BEJ.Bej.common.ApiResponse;
import com.BEJ.Bej.product.dto.request.CategoryRequest;
import com.BEJ.Bej.product.dto.response.CategoryResponse;
import com.BEJ.Bej.product.service.CategoryService;
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
@RequestMapping("/manage/category")
public class CategoryManageController {

    CategoryService categoryService;

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategoryResponse(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategory())
                .build();
    }

    @PostMapping("/add")
    ApiResponse<CategoryResponse> addNewCategory(@ModelAttribute @Valid CategoryRequest request) throws IOException {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.addNewCategory(request));

        return apiResponse;
    }

}
