package com.BEJ.Bej.cart;

import com.BEJ.Bej.cart.dto.response.cartResponse.CartItemResponse;
import com.BEJ.Bej.cart.entity.CartItem;
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
