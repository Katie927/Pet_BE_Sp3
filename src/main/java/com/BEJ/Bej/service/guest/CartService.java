package com.BEJ.Bej.service.guest;

import com.BEJ.Bej.dto.request.cartRequest.AddToCartRequest;
import com.BEJ.Bej.dto.response.cartResponse.CartItemResponse;
import com.BEJ.Bej.entity.cart.CartItem;
import com.BEJ.Bej.entity.identity.User;
import com.BEJ.Bej.entity.product.ProductAttribute;
import com.BEJ.Bej.exception.AppException;
import com.BEJ.Bej.exception.ErrorCode;
import com.BEJ.Bej.mapper.product.CartItemMapper;
import com.BEJ.Bej.repository.UserRepository;
import com.BEJ.Bej.repository.product.CartItemRepository;
import com.BEJ.Bej.repository.product.ProductAttributeRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CartService {

    UserRepository userRepository;
    ProductAttributeRepository productAttributeRepository;
    CartItemMapper cartItemMapper;
    CartItemRepository cartItemRepository;

    public CartItemResponse addtoCart(AddToCartRequest request){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        log.info(name);
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        ProductAttribute productA = productAttributeRepository.findById(request.getProductAttId()).orElseThrow(
                () -> new AppException(ErrorCode.UNAUTHENTICATED));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProductA(productA);
        cartItem.setQuantity(1);
        cartItem.setPrice(productA.getFinalPrice());

        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem));
    }

}
