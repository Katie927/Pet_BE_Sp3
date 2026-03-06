package com.BEJ.Bej.product.service;

import com.BEJ.Bej.product.dto.request.CategoryRequest;
import com.BEJ.Bej.product.dto.response.CategoryResponse;
import com.BEJ.Bej.product.entity.Category;
import com.BEJ.Bej.common.exception.AppException;
import com.BEJ.Bej.common.exception.ErrorCode;
import com.BEJ.Bej.product.mapper.CategoryMapper;
import com.BEJ.Bej.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategory(){
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }

    // add new
    public CategoryResponse addNewCategory(CategoryRequest request) throws IOException{
        if(categoryRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        System.out.println(request.getName());
        Category category = categoryMapper.toCategory(request);
        category.setName(request.getName());

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

}
