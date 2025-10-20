package com.BEJ.Bej.mapper.product;

import com.BEJ.Bej.dto.response.cartResponse.CartItemResponse;
import com.BEJ.Bej.entity.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "id", target = "id")
    CartItemResponse toCartItemResponse(CartItem cArtItem);

}
