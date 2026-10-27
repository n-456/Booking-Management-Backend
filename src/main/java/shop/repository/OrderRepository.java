package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import shop.dto.response.OrderDetailResponseDTO;
import shop.dto.response.OrderResponseDTO;
import shop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new shop.dto.response.OrderResponseDTO(o, c.name) FROM Order o JOIN Customer c ON o.customerId = c.id WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :customerName, '%'))")
    Page<OrderResponseDTO> searchOrdersWithCustomerName(@Param("customerName") String customerName, Pageable pageable);

    @Query("SELECT new shop.dto.response.OrderResponseDTO(o, c.name) FROM Order o LEFT JOIN Customer c ON o.customerId = c.id")
    Page<OrderResponseDTO> searchOrdersWithoutCustomerName(Pageable pageable);

    @Query("SELECT new shop.dto.response.OrderResponseDTO(o, c.name) FROM Order o LEFT JOIN Customer c ON o.customerId = c.id WHERE c.name IS NULL")
    Page<OrderResponseDTO> searchOrdersWithoutCustomer(Pageable pageable);

    @Query("SELECT new shop.dto.response.OrderDetailResponseDTO(od.id, p.name, p.category, p.unitPrice, p.img) " +
            "FROM Order o " +
            "JOIN OrderDetail od ON o.id = od.orderId " +
            "JOIN Product p ON od.productId = p.id " +
            "WHERE o.id = :orderId")
    Page<OrderDetailResponseDTO> findProductByOrderId(@Param("orderId") Long orderId, Pageable pageable);
}