package com.BEJ.Bej.controller.cart;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.service.guest.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    ApiResponse<CartResponse> addToCart(AddToCartResquest resquest){
        return ApiResponse.builder()
                .result(cartService.addToCart(resquest))
                .build();
    }

}
