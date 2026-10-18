package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.model.OrderDetail;
import shop.service.OrderDetailService;

@RestController
@RequestMapping("orderDetails")
@CrossOrigin(origins = "*")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrder(@PathVariable int id) {
        OrderDetail order = orderDetailService.getOrderDetailById(id);

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

    @GetMapping
    public ResponseEntity<Page<OrderDetail>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<OrderDetail> orderPage = orderDetailService.getAllOrderDetailsByPage(page, size, sortBy, sortDir);
        return ResponseEntity.ok(orderPage);
    }


    @PostMapping
    public ResponseEntity<OrderDetail> createOrder(@RequestBody OrderDetail request) {
        OrderDetail savedOrderDetail = orderDetailService.addOrderDetail(
                request.getOrderId(),
                request.getProductId(),
                request.getQuantity(),
                request.getUnitPrice(),
                request.getDiscount()
        );

        if (savedOrderDetail != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedOrderDetail);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrder(@PathVariable long id, @RequestBody OrderDetail request) {
        OrderDetail updatedOrderDetail = orderDetailService.updateOrderDetail(
                id,
                request.getOrderId(),
                request.getProductId(),
                request.getQuantity(),
                request.getUnitPrice(),
                request.getDiscount()
        );

        if (updatedOrderDetail != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedOrderDetail);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        boolean isDeleted = orderDetailService.deleteOrderDetail(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}