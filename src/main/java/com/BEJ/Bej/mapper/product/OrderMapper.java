package com.BEJ.Bej.mapper.product;

import com.BEJ.Bej.dto.request.cartRequest.OrderItemRequest;
import com.BEJ.Bej.dto.request.cartRequest.OrderRequest;
import com.BEJ.Bej.dto.response.cartResponse.OrderItemResponse;
import com.BEJ.Bej.dto.response.cartResponse.OrderResponse;
import com.BEJ.Bej.entity.cart.OrderItem;
import com.BEJ.Bej.entity.cart.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderItems", ignore = true)
    Orders toOrder(OrderRequest request);
    OrderResponse toOrderResponse(Orders orders);

    @Mapping(target = "productA", ignore = true)
    OrderItem toOrderItem(OrderItemRequest request);
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);


}
