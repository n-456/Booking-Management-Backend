package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.dao.CustomerDAO;
import shop.model.Customer;

import java.util.List;


@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public boolean addCustomer(String name, String phone, String mail, String pass){
        return customerDAO.addCustomer(name, phone, mail, pass);
    }

    public boolean updateCustomer(int id, String name, String phone, String mail, String pass) {
        return customerDAO.updateCustomer(id, name, phone, mail, pass);
    }

    public boolean deleteCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }
}
