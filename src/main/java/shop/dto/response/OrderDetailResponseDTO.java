package shop.dto.response;

import shop.model.Order;

public class OrderDetailResponseDTO {
    private String name;
    private String category;
    private int price;
    private String img;

    public OrderDetailResponseDTO(String name, String category, int price, String img) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}