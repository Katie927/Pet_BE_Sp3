package com.BEJ.Bej.mapper.product;

import com.BEJ.Bej.dto.response.cartResponse.CartItemResponse;
import com.BEJ.Bej.entity.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "productAttName", source = "productA.name")
    @Mapping(target = "attId", source = "productA.id")
    @Mapping(target = "img", expression = "java(getSecondImageUrl(cartItem))")
    CartItemResponse toCartItemResponse(CartItem cartItem);

    default String getSecondImageUrl(CartItem cartItem) {
        var images = cartItem.getProductA()
                .getVariant()
                .getDetailImages();
        if (images == null || images.isEmpty() || images.getFirst() == null) return null;
        return images.getFirst().getUrl();
    }

}
