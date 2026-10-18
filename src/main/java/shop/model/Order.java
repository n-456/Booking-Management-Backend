package shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customerId")
    private long customerId;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "status")
    private String status;

    @Column(name = "shippedDate")
    private LocalDate shippedDate;

    @Column(name = "shippingFee")
    private int shippingFee;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    Order(){}

    public Order(long customerId, LocalDate orderDate, String status, LocalDate shippedDate, int shippingFee, String paymentMethod) {
        this.customerId = customerId;
        this.shippingFee = shippingFee;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
    }

    public Order(long id, long customerId, LocalDate orderDate, String status, LocalDate shippedDate, int shippingFee, String paymentMethod, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.shippingFee = shippingFee;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.createdAt = createdAt;
    }


    public Order(Order order) {
        this.customerId = order.customerId;
        this.shippingFee = order.shippingFee;
        this.status = order.status;
        this.paymentMethod = order.paymentMethod;
        this.orderDate = order.orderDate;
        this.shippedDate = order.shippedDate;
    }

    public long getId() {
        return id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public int getShippingFee() {
        return shippingFee;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public void setShippingFee(int shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return customerId == order.customerId
                && shippingFee == order.shippingFee
                && Objects.equals(status, order.status)
                && Objects.equals(paymentMethod, order.paymentMethod)
                && Objects.equals(orderDate, order.orderDate)
                && Objects.equals(shippedDate, order.shippedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.customerId, this.shippingFee, this.status, this.paymentMethod, this.orderDate, this.shippedDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", shippingFee=" + shippingFee +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderDate=" + orderDate +
                ", shippedDate=" + shippedDate +
                ", createdAt=" + createdAt +
                '}';
    }
}