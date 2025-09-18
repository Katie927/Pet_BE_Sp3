package com.BEJ.Bej.service;

import com.BEJ.Bej.dto.response.productResponse.CategoryResponse;
import com.BEJ.Bej.mapper.CategoryMapper;
import com.BEJ.Bej.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    

}
