package com.BEJ.Bej.mapper;

import com.BEJ.Bej.dto.request.productRequest.ProductRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.entity.product.Product;
import com.BEJ.Bej.entity.product.ProductAttribute;
import com.BEJ.Bej.entity.product.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "image", ignore = true)
    Product toProduct(ProductRequest request);

//    @Mapping(source = "attributes", target = "attributes")
//    @Mapping(source = "detailImages", target = "detailImages")
//    @Mapping(source = "status", target = "status")
//    @Mapping(source = "color", target = "color")
    @Mapping(source = "id", target = "id")
    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    // Chuyển danh sách ProductAttribute thành danh sách String (chỉ lấy giá trị)
    default List<String> mapSpecs(List<ProductAttribute> attributes) {
        return attributes != null ?
                attributes.stream().map(ProductAttribute::getValue).collect(Collectors.toList())
                : null;
    }

    default  List<String> mapImages(List<ProductImage> images) {
        return images != null ?
                images.stream().map(ProductImage::getUrl).collect(Collectors.toList())
                : null;
    }
}
