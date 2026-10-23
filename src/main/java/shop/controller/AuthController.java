package shop.controller;

import shop.dto.request.LoginRequest;
import shop.dto.request.RegisterRequest;
import shop.model.Customer;
import shop.repository.CustomerRepository;
import shop.security.CustomerDetails;
import shop.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import shop.service.DomainCustomerDetailsService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository customserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DomainCustomerDetailsService customerDetailsService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (customserRepository.findByName(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username đã tồn tại!");
        }
        Customer customer = new Customer();
        customer.setName(request.getUsername());
        customer.setPass(passwordEncoder.encode(request.getPassword()));
//        customer.setRole("ROLE_USER");
        customserRepository.save(customer);
        return ResponseEntity.ok("Đăng ký thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        CustomerDetails customerDetails = customerDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(customerDetails);
        return ResponseEntity.ok(token);
    }
}