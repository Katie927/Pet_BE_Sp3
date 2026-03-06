package com.BEJ.Bej.order.service;

import com.BEJ.Bej.cart.dto.response.cartResponse.OrdersResponse;
import com.BEJ.Bej.cart.dto.response.orderResponse.RevenueStatisticsResponse;
import com.BEJ.Bej.cart.dto.response.orderResponse.TopProductResponse;
import com.BEJ.Bej.product.entity.ProductAttribute;
import com.BEJ.Bej.common.exception.AppException;
import com.BEJ.Bej.common.exception.ErrorCode;
import com.BEJ.Bej.order.OrderMapper;
import com.BEJ.Bej.identity.repository.UserRepository;
import com.BEJ.Bej.cart.repository.CartItemRepository;
import com.BEJ.Bej.order.repository.OrderRepository;
import com.BEJ.Bej.product.repository.ProductAttributeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class OrderService {

    ProductAttributeRepository productAttributeRepository;
    UserRepository userRepository;
    CartItemRepository cartItemRepository;

    OrderMapper orderMapper;

    OrderRepository orderRepository;
    OrderRepository orderItemRepository;

    public List<OrdersResponse> getOrdersByPhoneOrName(String phoneNumber){
        return orderRepository.searchByPhoneNumberOrderByOrderAtDesc(phoneNumber).stream().map(orderMapper::toOrdersResponse).toList();
    }

    public RevenueStatisticsResponse getRevenueStatistics(int year, Integer month) {
        log.info("📊 Getting revenue statistics - Year: {}, Month: {}", year, month);

        if (month != null && (month < 1 || month > 12)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        if (month != null) {
            // Thống kê theo tháng cụ thể
            return getMonthlyRevenue(year, month);
        } else {
            // Thống kê cả năm
            return getYearlyRevenue(year);
        }
    }

    private RevenueStatisticsResponse getMonthlyRevenue(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        Double totalRevenue = orderRepository.sumTotalPriceByOrderAtBetweenAndStatus(startDate, endDate);
        Long totalOrders = orderRepository.countByOrderAtBetween(startDate, endDate);
        Long repairOrder = orderRepository.countByOrderAtBetweenAndType(startDate, endDate, 1);
        Long saleOrder = orderRepository.countByOrderAtBetweenAndType(startDate, endDate, 0);

        if (totalRevenue == null) {
            totalRevenue = 0.0;
        }

        return RevenueStatisticsResponse.builder()
                .year(year)
                .month(month)
                .totalRevenue(totalRevenue)
                .totalOrders(totalOrders != null ? totalOrders : 0)
                .repairOrder(repairOrder)
                .saleOrder(saleOrder)
                .monthlyRevenues(null)
                .build();
    }

    private RevenueStatisticsResponse getYearlyRevenue(int year) {
        List<RevenueStatisticsResponse.MonthlyRevenue> monthlyRevenues = new ArrayList<>();
        double totalYearRevenue = 0.0;
        long totalYearOrders = 0;

        // Tính doanh thu cho từng tháng
        for (int m = 1; m <= 12; m++) {
            YearMonth yearMonth = YearMonth.of(year, m);
            LocalDate monthStart = yearMonth.atDay(1);
            LocalDate monthEnd = yearMonth.atEndOfMonth();

            Double monthRevenue = orderRepository.sumTotalPriceByOrderAtBetweenAndStatus(monthStart, monthEnd);
            Long monthOrders = orderRepository.countByOrderAtBetweenAndStatus(monthStart, monthEnd);

            if (monthRevenue == null) {
                monthRevenue = 0.0;
            }
            if (monthOrders == null) {
                monthOrders = 0L;
            }

            totalYearRevenue += monthRevenue;
            totalYearOrders += monthOrders;

            monthlyRevenues.add(RevenueStatisticsResponse.MonthlyRevenue.builder()
                    .month(m)
                    .monthName("Tháng " + m)
                    .revenue(monthRevenue)
                    .orderCount(monthOrders)
                    .build());
        }

        return RevenueStatisticsResponse.builder()
                .year(year)
                .month(null)
                .totalRevenue(totalYearRevenue)
                .totalOrders(totalYearOrders)
                .monthlyRevenues(monthlyRevenues)
                .build();
    }


    public TopProductResponse getTopProducts(Integer year, Integer month, Integer limit) {

        if (limit == null || limit <= 0) {
            limit = 10;
        }

        LocalDate startDate;
        LocalDate endDate;

        if (month != null && year != null) {
            YearMonth yearMonth = YearMonth.of(year, month);
            startDate = yearMonth.atDay(1);
            endDate = yearMonth.atEndOfMonth();
        } else if (year != null) {
            startDate = LocalDate.of(year, 1, 1);
            endDate = LocalDate.of(year, 12, 31);
        } else {
            // Lấy 1 năm gần nhất
            endDate = LocalDate.now();
            startDate = endDate.minusYears(1);
        }

        List<Object[]> results = orderItemRepository.findTopProductsByDateRange(startDate, endDate);

        List<TopProductResponse.TopProductItem> products = new ArrayList<>();
        int count = 0;

        for (Object[] result : results) {
            if (count >= limit) break;

            String productAttributeId = (String) result[0];
            Long totalSold = ((Number) result[1]).longValue();
            Double totalRevenue = ((Number) result[2]).doubleValue();

            ProductAttribute productAttribute = productAttributeRepository.findById(productAttributeId)
                    .orElse(null);

            if (productAttribute != null && productAttribute.getVariant() != null
                    && productAttribute.getVariant().getProduct() != null) {
                String productId = productAttribute.getVariant().getProduct().getId();
                String productName = productAttribute.getVariant().getProduct().getName();
                String productAttributeName = productAttribute.getName();
                String image = productAttribute.getVariant().getProduct().getImage();

                products.add(TopProductResponse.TopProductItem.builder()
                        .productId(productId)
                        .productName(productName)
                        .productAttributeId(productAttributeId)
                        .productAttributeName(productAttributeName)
                        .totalSold(totalSold)
                        .totalRevenue(totalRevenue)
                        .image(image)
                        .build());
                count++;
            }
        }

        return TopProductResponse.builder()
                .products(products)
                .limit(limit)
                .build();
    }
}
