package com.BEJ.Bej.controller.cart;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.cartRequest.OrderRequest;
import com.BEJ.Bej.dto.response.cartResponse.CartItemResponse;
import com.BEJ.Bej.dto.response.cartResponse.OrderResponse;
import com.BEJ.Bej.service.guest.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    @PostMapping("/add/{attId}")
    ApiResponse<CartItemResponse> addToCart(@PathVariable String attId){
        return ApiResponse.<CartItemResponse>builder()
                .result(cartService.addToCart(attId))
                .build();
    }

    @GetMapping("/view")
    ApiResponse<List<CartItemResponse>> viewCart(){
        return ApiResponse.<List<CartItemResponse>>builder()
                .result(cartService.viewCart())
                .build();
    }

    @PostMapping("/place-order")
    ApiResponse<OrderResponse> placeOrder(@RequestBody OrderRequest request){
        return ApiResponse.<OrderResponse>builder()
                .result(cartService.placeOrder(request))
                .build();
    }

}
