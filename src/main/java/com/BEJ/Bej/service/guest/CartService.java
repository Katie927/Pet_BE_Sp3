package com.BEJ.Bej.service.guest;

import com.BEJ.Bej.dto.request.cartRequest.OrderItemRequest;
import com.BEJ.Bej.dto.request.cartRequest.OrderRequest;
import com.BEJ.Bej.dto.response.cartResponse.CartItemResponse;
import com.BEJ.Bej.dto.response.cartResponse.OrderResponse;
import com.BEJ.Bej.entity.cart.CartItem;
import com.BEJ.Bej.entity.cart.OrderItem;
import com.BEJ.Bej.entity.cart.Orders;
import com.BEJ.Bej.entity.identity.User;
import com.BEJ.Bej.entity.product.ProductAttribute;
import com.BEJ.Bej.exception.AppException;
import com.BEJ.Bej.exception.ErrorCode;
import com.BEJ.Bej.mapper.product.CartItemMapper;
import com.BEJ.Bej.mapper.product.OrderMapper;
import com.BEJ.Bej.repository.UserRepository;
import com.BEJ.Bej.repository.product.CartItemRepository;
import com.BEJ.Bej.repository.product.OrderRepository;
import com.BEJ.Bej.repository.product.ProductAttributeRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CartService {

    UserRepository userRepository;
    ProductAttributeRepository productAttributeRepository;
    CartItemRepository cartItemRepository;
    OrderRepository ordersRepository;

    OrderMapper orderMapper;
    CartItemMapper cartItemMapper;

    public CartItemResponse addToCart(String attId){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        log.info(name);
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        ProductAttribute productA = productAttributeRepository.findById(attId).orElseThrow(
                () -> new AppException(ErrorCode.UNAUTHENTICATED));

        CartItem cartItem = cartItemRepository.findByUser_IdAndProductA_Id(user.getId(), productA.getId());

        if( cartItem == null){
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProductA(productA);
            cartItem.setColor(productA.getVariant().getColor());
            cartItem.setQuantity(1);
            cartItem.setPrice(productA.getFinalPrice());
            cartItem.setProductName(productA.getVariant().getProduct().getName());
            cartItem.setImg(productA.getVariant().getDetailImages().getFirst().getUrl());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem));
    }

    public List<CartItemResponse> viewCart(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        log.info(name);
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return cartItemRepository.findAllByUserId(user.getId()).stream().map(cartItemMapper::toCartItemResponse).toList();
    }

    public OrderResponse placeOrder(OrderRequest request) {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        log.info(name);
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Orders orders = orderMapper.toOrder(request);
        orders.setUser(user);

        List<OrderItem> orderItems = request.getItems().stream().map(itemReq -> {
            OrderItem orderItem = orderMapper.toOrderItem(itemReq);

            ProductAttribute productAtt = productAttributeRepository
                    .findById(itemReq.getProductAttId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

            orderItem.setProductA(productAtt);
            orderItem.setOrder(orders);
            return orderItem;
        }).toList();

        orders.setOrderItems(orderItems);
        orders.setOrderAt(LocalDate.now());

        Orders saved = ordersRepository.save(orders);

        return orderMapper.toOrderResponse(saved);
    }
}
