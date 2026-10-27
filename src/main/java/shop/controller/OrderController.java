package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.dto.response.OrderDetailResponseDTO;
import shop.dto.response.OrderResponseDTO;
import shop.model.Order;
import shop.service.OrderService;

@RestController
@RequestMapping("orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        Order order = orderService.getOrderById(id);

        if(order != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(order);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping ("/nocustomer")
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrdersWithoutCustomer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<OrderResponseDTO> orderPage = orderService.getAllOrdersWithoutCustomerByPage(page, size, sortBy, sortDir);
        return ResponseEntity.ok(orderPage);
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(
            @RequestParam(required = false) String customerName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<OrderResponseDTO> orderPage = orderService.getAllOrdersByPage(customerName, page, size, sortBy, sortDir);
        return ResponseEntity.ok(orderPage);
    }

    @GetMapping("/products")
    public ResponseEntity<Page<OrderDetailResponseDTO>> getAllProductsByOrderId(
            @RequestParam Long orderId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<OrderDetailResponseDTO> orderPage = orderService.getAllProductWithOrderIdByPage(orderId, page, size, sortBy, sortDir);
        return ResponseEntity.ok(orderPage);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order request) {
        Order savedOrder = orderService.addOrder(
                request.getCustomerId(),
                request.getOrderDate(),
                request.getStatus(),
                request.getShippedDate(),
                request.getShippingFee(),
                request.getPaymentMethod()
        );

        if (savedOrder != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedOrder);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Order request) {
        Order updatedOrder = orderService.updateOrder(
                id,
                request.getCustomerId(),
                request.getOrderDate(),
                request.getStatus(),
                request.getShippedDate(),
                request.getShippingFee(),
                request.getPaymentMethod()
        );

        if (updatedOrder != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedOrder);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        boolean isDeleted = orderService.deleteOrder(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}