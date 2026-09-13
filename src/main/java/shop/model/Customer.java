package shop.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Customer {
    private int id;
    private String name, phone, email, pass;
    private LocalDateTime createdAt;

    Customer(){}

    public Customer(String name, String phone, String email, String pass) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
    }

    public Customer(int id, String name, String phone, String email, String pass, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
        this.createdAt = createdAt;
    }

    public Customer(Customer customer) {
        this.name = customer.name;
        this.phone = customer.phone;
        this.email = customer.email;
        this.pass = customer.pass;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customer))
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(this.name, customer.name)
                && Objects.equals(this.phone, customer.phone) && Objects.equals(this.email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.phone, this.email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
