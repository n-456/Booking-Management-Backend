package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import shop.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getAllCustomer(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);

        if(customer != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(customer);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer() {
        List<Customer> customers = customerService.getAllCustomers();

        if(customers != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(customers);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer request) {
        Customer savedCustomer = customerService.addCustomer(
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getPass()
        );

        if (savedCustomer != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedCustomer);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer request) {
        Customer updatedCustomer = customerService.updateCustomer(
                id,
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getPass()
        );

        if (updatedCustomer != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedCustomer);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        boolean isDeleted = customerService.deleteCustomer(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}