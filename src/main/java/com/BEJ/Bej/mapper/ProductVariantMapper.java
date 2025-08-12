package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.productRequest.ProductVariantRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.dto.response.productResponse.ProductVariantResponse;
import com.BEJ.Bej.entity.product.Product;
import com.BEJ.Bej.entity.product.ProductImage;
import com.BEJ.Bej.entity.product.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    @Mapping(target = "detailImages", ignore = true)
    ProductVariant toVariant(ProductVariantRequest request);

//    @Mapping(source = "id", target = "id")
    @Mapping(source = "color", target = "color")
    ProductVariantResponse toVariantResponse(ProductVariant variant);

    List<ProductVariantResponse> toVariantResponseList(List<ProductVariant> variants);

    default  List<String> mapImages(List<ProductImage> images) {
        return images != null ?
                images.stream().map(ProductImage::getUrl).collect(Collectors.toList())
                : null;
    }
}
