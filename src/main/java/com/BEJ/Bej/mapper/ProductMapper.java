package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.response.ProductResponse;
import com.BEJ.Bej.entity.product.Product;
import com.BEJ.Bej.entity.product.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "attributes", target = "specs")
    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    // Chuyển danh sách ProductAttribute thành danh sách String (chỉ lấy giá trị)
    default List<String> mapSpecs(List<ProductAttribute> attributes) {
        return attributes != null ?
                attributes.stream().map(ProductAttribute::getValue).collect(Collectors.toList())
                : null;
    }
}
