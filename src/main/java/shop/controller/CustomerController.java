//package shop.controller;
//
//import shop.dao.CustomerDAO;
//import shop.model.Customer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("customers")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
//
//public class CustomerController {
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Customer> getAllCustomer(@PathVariable int id) {
//        Customer customer = CustomerDAO.getCustomerById(id);
//
//        if(customer != null) {
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(customer);
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(null);
//        }
//    }
//
//    @GetMapping
//    ResponseEntity<List<Customer>> getCustomer() {
//        List<Customer> customers = CustomerDAO.getAllCustomers();
//
//        if(customers != null) {
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(customers);
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(null);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer request) {
//        boolean isInserted = CustomerDAO.insertCustomer(
//                request.getName(),
//                request.getPhone(),
//                request.getEmail(),
//                request.getPass()
//        );
//
//        if (isInserted) {
//            return ResponseEntity
//                    .status(HttpStatus.CREATED)
//                    .body(request);
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer request) {
//        boolean isUpdated = CustomerDAO.updateCustomer(id, request.getName());
//
//        if (isUpdated) {
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(request);
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
//        boolean isDeleted = CustomerDAO.deleteCustomer(id);
//
//        if (isDeleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}


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
//@CrossOrigin(origins = "http://localhost:5500")

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
    ResponseEntity<List<Customer>> getCustomer() {
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
        boolean isInserted = customerService.addCustomer(
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getPass()
        );

        if (isInserted) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(request);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer request) {
        boolean isUpdated = customerService.updateCustomer(id, request.getName(), request.getPhone(), request.getEmail(), request.getPass());

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(request);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
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
