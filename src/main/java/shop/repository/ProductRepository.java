package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Derived Query Method: Spring Data tự sinh câu lệnh dựa trên tên method
    // Tìm kiếm theo tên (gần đúng) có phân trang
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}