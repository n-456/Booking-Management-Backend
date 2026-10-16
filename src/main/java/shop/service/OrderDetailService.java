package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.model.OrderDetail;
import shop.repository.OrderDetailRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail getOrderDetailById(long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail addOrderDetail(long orderId, long productId, int quantity, int unitPrice, int discount) {
        OrderDetail orderDetail = new OrderDetail(orderId, productId, quantity, unitPrice, discount);
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail updateOrderDetail(long id, long orderId, long productId, int quantity, int unitPrice, int discount) {
        Optional<OrderDetail> existingOrder = orderDetailRepository.findById(id);
        if (existingOrder.isPresent()) {
            OrderDetail orderDetail = existingOrder.get();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productId);
            orderDetail.setQuantity(quantity);
            orderDetail.setUnitPrice(unitPrice);
            orderDetail.setDiscount(discount);
            return orderDetailRepository.save(orderDetail);
        }
        return null;
    }

    public boolean deleteOrderDetail(long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<OrderDetail> getAllOrderDetailsByPage(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return orderDetailRepository.findAll(pageable);

    }
}

