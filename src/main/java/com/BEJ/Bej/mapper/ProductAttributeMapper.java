package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.productRequest.ProductAttributeRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductAttributeResponse;
import com.BEJ.Bej.entity.product.ProductAttribute;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    ProductAttribute toProductAttribute(ProductAttributeRequest request);

    ProductAttributeResponse toProductAttributeResponse(ProductAttribute attribute);
    List<ProductAttributeResponse> toAttributeList(List<ProductAttribute> attributes);


}
