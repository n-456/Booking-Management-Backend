package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.repository.CustomerRepository;
import shop.model.Customer;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(String name, String phone, String mail, String pass) {
        Customer customer = new Customer(name, phone, mail, pass);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(long id, String name, String phone, String mail, String pass) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(name);
            customer.setPhone(phone);
            customer.setEmail(mail);
            customer.setPass(pass);
            return customerRepository.save(customer);
        }
        return null;
    }

    public boolean deleteCustomer(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}