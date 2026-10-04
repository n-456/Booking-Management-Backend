package shop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.model.Customer;
import shop.service.CustomerService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowCredentials = "true")
//@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true") // credentails

public class AuthController {

    private final CustomerService customerService;


    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer loginRequest, HttpServletRequest request) {
        Customer customer = customerService.checkLogin(loginRequest.getEmail(), loginRequest.getPass());

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", customer);

            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(401).body("Email or pass incorrect!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logout successfully!");
    }

}