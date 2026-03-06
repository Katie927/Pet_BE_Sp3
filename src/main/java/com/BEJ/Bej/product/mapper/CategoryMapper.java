package com.BEJ.Bej.product.mapper;

import com.BEJ.Bej.product.dto.request.CategoryRequest;
import com.BEJ.Bej.product.dto.response.CategoryResponse;
import com.BEJ.Bej.product.dto.response.VariantSummaryResponse;
import com.BEJ.Bej.product.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductVariantMapper.class, VariantSummaryResponse.class})

public interface CategoryMapper {

    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryRequest request);

    @Mapping(source = "id", target = "id")
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> toResponseList(List<Category> categories);

}
