package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.dto.response.OrderDetailResponseDTO;
import shop.dto.response.OrderResponseDTO;
import shop.model.Order;
import shop.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Long customerId, LocalDate orderDate, String status, LocalDate shippedDate, int shippingFee, String paymentMethod) {
        Order order = new Order(customerId, orderDate, status, shippedDate, shippingFee, paymentMethod);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Long customerId, LocalDate orderDate, String status, LocalDate shippedDate, int shippingFee, String paymentMethod) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setCustomerId(customerId);
            order.setOrderDate(orderDate);
            order.setStatus(status);
            order.setShippedDate(shippedDate);
            order.setShippingFee(shippingFee);
            order.setPaymentMethod(paymentMethod);
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean deleteOrder(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<OrderResponseDTO> getAllOrdersByPage(String customerName, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (customerName != null && !customerName.trim().isEmpty()) {
            return orderRepository.searchOrdersWithCustomerName(customerName, pageable);

        } else {
            return orderRepository.searchOrdersWithoutCustomerName(pageable);
        }
    }

    public Page<OrderResponseDTO> getAllOrdersWithoutCustomerByPage(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return orderRepository.searchOrdersWithoutCustomer(pageable);

    }

    public Page<OrderDetailResponseDTO> getAllProductWithOrderIdByPage(Long orderId, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return orderRepository.findProductByOrderId(orderId, pageable);

    }
}

