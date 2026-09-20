package shop.dao;

import org.springframework.stereotype.Repository;
import shop.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomerDAO {

    private HashMap<Integer, Customer> customerHashMap;

    public CustomerDAO(HashMap<Integer, Customer> customerHashMap) {
        this.customerHashMap = customerHashMap;
    }

    // CREATE - Tạo bảng
    public void createTable() {

    }

    // INSERT - Thêm customer mới
    public boolean addCustomer(String name, String phone, String email, String pass) {
        Customer customer = new Customer(name, phone, email, pass);
        int newId = customerHashMap.size();
        customerHashMap.put(newId, customer);
        return true;
    }

    // SELECT - Lấy tất cả customer
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>(customerHashMap.values());
        return customers;
    }

    // SELECT - Lấy customer theo ID
    public Customer getCustomerById(int id) {
        if (customerHashMap.containsKey(id))
            return (Customer) customerHashMap.get(id);
        return null;
    }

    // UPDATE - Cập nhật customer
    public boolean updateCustomer(int id, String name, String phone, String email, String pass) {
        Customer customer = new Customer(name, phone, email, pass);
        customerHashMap.put(id, customer);
        return false;
    }

    // DELETE - Xóa customer
    public boolean deleteCustomer(int id) {
        customerHashMap.remove(id);
        return true;
    }
}