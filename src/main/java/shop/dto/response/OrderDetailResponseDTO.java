package shop.dto.response;

public class OrderDetailResponseDTO {
    private Long id;
    private String name;
    private String category;
    private int price;
    private String img;

    public OrderDetailResponseDTO(Long id, String name, String category, int price, String img) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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