package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}