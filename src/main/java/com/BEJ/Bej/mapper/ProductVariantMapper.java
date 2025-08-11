package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.productRequest.ProductVariantRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.dto.response.productResponse.ProductVariantResponse;
import com.BEJ.Bej.entity.product.Product;
import com.BEJ.Bej.entity.product.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    @Mapping(target = "detailImages", ignore = true)
    ProductVariant toVariant(ProductVariantRequest request);

//    @Mapping(source = "id", target = "id")
//    ProductVariantResponse toVariantResponse(ProductVariant variant);
//
//    List<ProductVariantResponse> toVariantResponseList(List<ProductVariant> variants);

}
