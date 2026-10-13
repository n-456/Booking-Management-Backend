package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import shop.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN Customer c ON o.customerId = c.id WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :customerName, '%'))")
    Page<Order> searchOrdersByCustomerName(@Param("customerName") String customerName, Pageable pageable);
}