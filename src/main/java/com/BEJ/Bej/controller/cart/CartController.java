package com.BEJ.Bej.controller.cart;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.cartRequest.OrderRequest;
import com.BEJ.Bej.dto.response.cartResponse.CartItemResponse;
import com.BEJ.Bej.dto.response.cartResponse.OrderDetailsResponse;
import com.BEJ.Bej.service.guest.CartService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @PostMapping("/place-order")
    ApiResponse<OrderDetailsResponse> placeOrder(@RequestBody OrderRequest request){
        return ApiResponse.<OrderDetailsResponse>builder()
                .result(cartService.placeOrder(request))
                .build();
    }

    @GetMapping("/my-order")
    ApiResponse<List<OrderDetailsResponse>> getMyOrders(){
        return ApiResponse.<List<OrderDetailsResponse>>builder()
                .result(cartService.getMyOrder())
                .build();
    }

}
