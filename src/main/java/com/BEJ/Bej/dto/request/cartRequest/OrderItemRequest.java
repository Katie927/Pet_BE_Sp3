package com.BEJ.Bej.dto.request.cartRequest;

import com.BEJ.Bej.entity.cart.Orders;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequest {

    String productAttId;
    String cartItemId;

    int quantity;
}

/*
@Transactional
public OrderDetailsResponse placeOrder(OrderRequest request) {
    var context = SecurityContextHolder.getContext();
    String name = context.getAuthentication().getName();

    User user = userRepository.findByPhoneNumber(name)
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

    Orders orders = orderMapper.toOrder(request);
    orders.setUser(user);

    List<OrderItem> orderItems = new ArrayList<>();

    for (var itemReq : request.getItems()) {

        ProductAttribute productAtt = productAttributeRepository
                .findById(itemReq.getProductAttId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ATTRIBUTE_NOT_FOUND));

        // validate stock
        if (productAtt.getQuantity() < itemReq.getQuantity()) {
            throw new AppException(ErrorCode.OUT_OF_STOCK);
        }

        OrderItem orderItem = orderMapper.toOrderItem(itemReq);
        orderItem.setProductA(productAtt);
        orderItem.setOrder(orders);

        // ép giá server-side
        orderItem.setPrice(productAtt.getPrice());

        // update tồn kho
        productAtt.setQuantity(productAtt.getQuantity() - itemReq.getQuantity());
        productAttributeRepository.save(productAtt);

        // delete cart item
        cartItemRepository.deleteById(itemReq.getCartItemId());

        orderItems.add(orderItem);
    }

    double totalPrice = orderItems.stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();

    orders.setTotalPrice(totalPrice);
    orders.setOrderItems(orderItems);
    orders.setOrderAt(LocalDateTime.now());

    Orders saved = ordersRepository.save(orders);
    return orderMapper.toOrderDetailsResponse(saved);
}

 */
