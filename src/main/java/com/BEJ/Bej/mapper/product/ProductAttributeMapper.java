package com.BEJ.Bej.mapper.product;

import com.BEJ.Bej.dto.request.productRequest.ProductAttributeRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductAttributeResponse;
import com.BEJ.Bej.entity.product.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    ProductAttribute toProductAttribute(ProductAttributeRequest request);

//    @Mapping(source = "id", target = "id")
    ProductAttributeResponse toProductAttributeResponse(ProductAttribute attribute);
    List<ProductAttributeResponse> toAttributeList(List<ProductAttribute> attributes);

}
