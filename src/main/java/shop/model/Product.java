package shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "unitPrice")
    private int unitPrice;

    @Column(name = "cost")
    private int cost;

    @Column(name = "discontinued")
    private boolean discontinued;

    @Column(name = "stock")
    private int stock;

    @Column(name = "img")
    private String img;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;


    Product(){}

    public Product(String name, String category, int unitPrice, int cost, boolean discontinued, int stock, String img) {
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.cost = cost;
        this.discontinued = discontinued;
        this.stock = stock;
        this.img = img;
    }

    public Product(int id, String name, String category, int unitPrice, int cost, boolean discontinued, int stock, String img, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.cost = cost;
        this.discontinued = discontinued;
        this.stock = stock;
        this.img = img;
        this.createdAt = createdAt;
    }

    public Product(Product product) {
        this.unitPrice = product.unitPrice;
        this.cost = product.cost;
        this.stock = product.stock;
        this.name = product.name;
        this.img = img;
        this.category = product.category;
        this.discontinued = product.discontinued;
    }

    public long getId() {
        return id;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public String getImg() {
        return img;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return unitPrice == product.unitPrice
                && cost == product.cost
                && stock == product.stock
                && discontinued == product.discontinued
                && Objects.equals(name, product.name)
                && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.unitPrice, this.cost, this.stock, this.name, this.category, this.discontinued, this.img);
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", unit_price=" + unitPrice +
                ", cost=" + cost +
                ", stock=" + stock +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", discontinued=" + discontinued +
                ", img=" + img +
                ", createdAt=" + createdAt +
                '}';
    }
}
