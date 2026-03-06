package com.BEJ.Bej.order.repository;

import com.BEJ.Bej.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findAllByUserId(String userId);
    List<Orders> findAllByOrderByOrderAtDesc();

    @Query("""
        SELECT p FROM Orders p
        WHERE LOWER(p.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%'))
        ORDER BY p.orderAt DESC""")
    List<Orders> searchByPhoneNumberOrderByOrderAtDesc(
            @Param("phoneNumber") String phoneNumber
    );

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Orders o WHERE o.orderAt BETWEEN :startDate AND :endDate AND o.status IN (2, 5)")
    Double sumTotalPriceByOrderAtBetweenAndStatus(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(o) FROM Orders o WHERE o.orderAt BETWEEN :startDate AND :endDate")
    Long countByOrderAtBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(o) FROM Orders o WHERE o.orderAt BETWEEN :startDate AND :endDate AND o.type = :type")
    Long countByOrderAtBetweenAndType(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate,
                                      @Param("type") int type);

    @Query("SELECT COUNT(o) FROM Orders o WHERE o.orderAt BETWEEN :startDate AND :endDate AND o.status IN (2, 5)")
    Long countByOrderAtBetweenAndStatus(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT oi.productA.id, SUM(oi.quantity) as totalSold, SUM(oi.price * oi.quantity) as totalRevenue " +
            "FROM OrderItem oi " +
            "WHERE oi.order.orderAt BETWEEN :startDate AND :endDate " +
//           "AND oi.order.status IN (2, 5) " +
//           "AND oi.order.type = 0 " +
            "GROUP BY oi.productA.id " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTopProductsByDateRange(@Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);
}
