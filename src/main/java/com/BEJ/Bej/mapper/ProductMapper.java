package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.response.ProductResponse;
import com.BEJ.Bej.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}
