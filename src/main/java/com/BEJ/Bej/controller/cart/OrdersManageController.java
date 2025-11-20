package com.BEJ.Bej.controller.cart;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.cartRequest.OrderStatusRequest;
import com.BEJ.Bej.dto.response.cartResponse.OrderDetailsResponse;
import com.BEJ.Bej.dto.response.cartResponse.OrdersResponse;
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
@RequestMapping("/manage/orders")
public class OrdersManageController {

    CartService cartService;

    @GetMapping("/get-all")
    ApiResponse<List<OrdersResponse>> getAllOrders(){
        return ApiResponse.<List<OrdersResponse>>builder()
                .result(cartService.getAllOrders())
                .build();
    }

    @GetMapping("/details/{orderId}")
    ApiResponse<OrderDetailsResponse> getOrderDetails(@PathVariable String orderId){
        return ApiResponse.<OrderDetailsResponse>builder()
                .result(cartService.getOrderDetails(orderId))
                .build();
    }

    @Transactional
    @PutMapping("/update-order-status/{orderId}")
    ApiResponse<OrdersResponse> updateOrder(@RequestBody OrderStatusRequest request, @PathVariable String orderId){
        return ApiResponse.<OrdersResponse>builder()
                .result(cartService.updateOrderStatus(request, orderId))
                .build();
    }

}
