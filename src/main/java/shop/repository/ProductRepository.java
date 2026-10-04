package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import shop.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Derived Query Method: Spring Data tự sinh câu lệnh dựa trên tên method
    // Tìm kiếm theo tên (gần đúng) có phân trang
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // 2. Custom @Query: Viết câu lệnh JPQL thủ công khi cần logic phức tạp
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.unitPrice >= :minPrice")
    Page<Product> filterByCategoryAndPrice(
            @Param("category") String category,
            @Param("minPrice") Double minPrice,
            Pageable pageable
    );
}