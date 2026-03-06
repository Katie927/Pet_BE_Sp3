package com.BEJ.Bej.product.mapper;

import com.BEJ.Bej.product.dto.response.ProductImageResponse;
import com.BEJ.Bej.product.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    ProductImage toProductImage(ProductImageResponse request);

    @Mapping(source = "id", target = "id")
    ProductImageResponse toProductImageResponse(ProductImage image);

}
