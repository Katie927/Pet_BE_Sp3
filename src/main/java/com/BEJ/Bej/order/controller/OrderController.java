package com.BEJ.Bej.order.controller;

import com.BEJ.Bej.common.ApiResponse;
import com.BEJ.Bej.cart.dto.response.cartResponse.OrderDetailsResponse;
import com.BEJ.Bej.cart.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/orders")
public class OrderController {

    CartService cartService;

    @GetMapping("/my-orders")
    ApiResponse<List<OrderDetailsResponse>> getMyOrders() {
        log.info("📦 Getting my orders");
        List<OrderDetailsResponse> result = cartService.getMyOrder();
        log.info("✅ Retrieved {} orders", result.size());
        return ApiResponse.<List<OrderDetailsResponse>>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{orderId}")
    ApiResponse<OrderDetailsResponse> getOrderDetails(@PathVariable String orderId) {
        log.info("📦 Getting order details - ID: {}", orderId);
        OrderDetailsResponse result = cartService.getMyOrderDetails(orderId);
        log.info("✅ Order details retrieved - ID: {}", orderId);
        return ApiResponse.<OrderDetailsResponse>builder()
                .result(result)
                .build();
    }

//    @PutMapping("/repair-order/{orderId}/confirm")
//    ApiResponse<OrdersResponse> confirmRepairOrder(@PathVariable String orderId){
//        return ApiResponse.<OrdersResponse>builder()
//                .result(cartService.confirmRepairOrder(orderId))
//                .build();
//    }
}
