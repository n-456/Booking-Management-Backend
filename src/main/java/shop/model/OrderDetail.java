package shop.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "orderId")
    private long orderId;

    @Column(name = "productId")
    private long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unitPrice")
    private int unitPrice;

    @Column(name = "discount")
    private int discount;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    OrderDetail(){}

    public OrderDetail(long orderId, long productId, int quantity, int unitPrice, int discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public OrderDetail(long id, long orderId, long productId, int quantity, int unitPrice, int discount, LocalDateTime createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.createdAt = createdAt;
    }

    public OrderDetail(OrderDetail orderDetail) {
        this.id = orderDetail.id;
        this.orderId = orderDetail.orderId;
        this.productId =orderDetail.productId;
        this.quantity = orderDetail.quantity;
        this.unitPrice = orderDetail.unitPrice;
        this.discount = orderDetail.discount;
        this.createdAt = orderDetail.createdAt;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return id == that.id && orderId == that.orderId && productId == that.productId && quantity == that.quantity && unitPrice == that.unitPrice && discount == that.discount && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, quantity, unitPrice, discount, createdAt);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", createdAt=" + createdAt +
                '}';
    }
}