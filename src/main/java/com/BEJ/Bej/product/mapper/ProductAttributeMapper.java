package com.BEJ.Bej.product.mapper;

import com.BEJ.Bej.product.dto.request.ProductAttributeRequest;
import com.BEJ.Bej.product.dto.response.ProductAttributeResponse;
import com.BEJ.Bej.product.entity.ProductAttribute;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    ProductAttribute toProductAttribute(ProductAttributeRequest request);

//    @Mapping(source = "id", target = "id")
    ProductAttributeResponse toProductAttributeResponse(ProductAttribute attribute);
    List<ProductAttributeResponse> toAttributeList(List<ProductAttribute> attributes);

}
