package shop.dto.response;

import shop.model.Order;

public class OrderResponseDTO {
    private Order order;
    private String customerName;

    public OrderResponseDTO(Order order, String customerName) {
        this.order = order;
        this.customerName = customerName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}