package com.BEJ.Bej.mapper.product;

import com.BEJ.Bej.dto.request.cartRequest.OrderItemRequest;
import com.BEJ.Bej.dto.request.cartRequest.OrderRequest;
import com.BEJ.Bej.dto.response.cartResponse.OrderItemResponse;
import com.BEJ.Bej.dto.response.cartResponse.OrderDetailsResponse;
import com.BEJ.Bej.dto.response.cartResponse.OrdersResponse;
import com.BEJ.Bej.entity.cart.OrderItem;
import com.BEJ.Bej.entity.cart.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderItems", ignore = true)
    Orders toOrder(OrderRequest request);
    @Mapping(target = "userName", source = "user.fullName")
    OrdersResponse toOrdersResponse(Orders orders);
    @Mapping(target = "userName", source = "user.fullName")
    OrderDetailsResponse toOrderDetailsResponse(Orders orders);

    @Mapping(target = "productA", ignore = true)
    OrderItem toOrderItem(OrderItemRequest request);
    @Mapping(target = "img", expression = "java(resolveImg(orderItem))")
    @Mapping(target = "productAttName", source = "productA.name")
    @Mapping(target = "color", source = "productA.variant.color")
    @Mapping(target = "productName", source = "productA.variant.product.name")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    default String resolveImg(OrderItem orderItem) {
        if(orderItem.getProductA() == null
                || orderItem.getProductA().getVariant() == null
                || orderItem.getProductA().getVariant().getDetailImages() == null
                || orderItem.getProductA().getVariant().getDetailImages().isEmpty())
            return null;

        return orderItem.getProductA().getVariant().getDetailImages().getFirst().getUrl();
    }

}

//{
//        "code": 1000,
//        "result": {
//        "id": "f0889d76-ac83-4fba-ac1c-d29a03aef7a2",
//        "phoneNumber": "01234123413",
//        "email": "adu112@gmail.com",
//        "address": "Hn",
//        "updatedAt": null,
//        "orderAt": "2025-11-09",
//        "description": "giao hang",
//        "orderItems": [
//        {
//        "img": null,
//        "productAttName": null,
//        "quantity": 1,
//        "price": 2.34234234E8,
//        "color": null,
//        "productName": null
//        },
//        {
//        "img": null,
//        "productAttName": null,
//        "quantity": 1,
//        "price": 2.34234234E8,
//        "color": null,
//        "productName": null
//        }
//        ]
//        }
//        }