package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.response.productResponse.ProductImageResponse;
import com.BEJ.Bej.entity.product.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    ProductImage toProductImage(ProductImageResponse request);

    @Mapping(source = "id", target = "id")
    ProductImageResponse toProductImageResponse(ProductImage image);

}
