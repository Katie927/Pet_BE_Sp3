package com.BEJ.Bej.order.controller;

import com.BEJ.Bej.common.ApiResponse;
import com.BEJ.Bej.cart.dto.request.cartRequest.OrderStatusRequest;
import com.BEJ.Bej.cart.dto.response.cartResponse.OrderDetailsResponse;
import com.BEJ.Bej.cart.dto.response.cartResponse.OrdersResponse;
import com.BEJ.Bej.cart.dto.response.orderResponse.RevenueStatisticsResponse;
import com.BEJ.Bej.cart.dto.response.orderResponse.TopProductResponse;
import com.BEJ.Bej.cart.service.CartService;
import com.BEJ.Bej.order.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/manage/orders")
public class OrdersManageController {

    CartService cartService;
    OrderService orderService;

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

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<List<OrdersResponse>> getOrdersByPhoneOrName(@RequestParam(required = false) String phoneNumber){
        return ApiResponse.<List<OrdersResponse>>builder()
                .result(orderService.getOrdersByPhoneOrName(phoneNumber))
                .build();
    }


    // statistc
    @GetMapping("/revenue-statistics")
    ApiResponse<RevenueStatisticsResponse> getRevenueStatistics(
            @RequestParam int year,
            @RequestParam(required = false) Integer month) {

        RevenueStatisticsResponse result = orderService.getRevenueStatistics(year, month);

        return ApiResponse.<RevenueStatisticsResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/top-products")
    ApiResponse<TopProductResponse> getTopProducts(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer limit) {

        TopProductResponse result = orderService.getTopProducts(year, month, limit);

        return ApiResponse.<TopProductResponse>builder()
                .result(result)
                .build();
    }
}
