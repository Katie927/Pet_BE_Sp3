package com.BEJ.Bej.controller.manage;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.productRequest.CategoryRequest;
import com.BEJ.Bej.dto.response.productResponse.CategoryResponse;
import com.BEJ.Bej.service.CategoryService;
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
@RequestMapping("/admin/category")
public class CategoryManageController {

    CategoryService categoryService;

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategoryResponse(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategory())
                .build();
    }

    @PostMapping("/add")
    ApiResponse<CategoryResponse> addNewCategory(@RequestBody @Valid CategoryRequest request) throws IOException {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.addNewCategory(request));

        return apiResponse;
    }

}
