package shop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.model.Customer;
import shop.service.CustomerService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = {
                "http://localhost:5500",
                "https://n-456.github.io/Booking-Management-Frontend/"
        },
        allowCredentials = "true"
)


public class AuthController {

    private final CustomerService customerService;


    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // api đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer loginRequest, HttpServletRequest request) {
        // Kiểm tra thông tin trong database
        Customer customer = customerService.checkLogin(loginRequest.getEmail(), loginRequest.getPass());

        if (customer != null) {
            // Tạo session và lưu thông tin user vào session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", customer);

            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng!");
        }
    }

    // api đăng xuất
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Lấy session hiện tại nhưng không tạo mới (nếu có)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Hủy bỏ session
        }
        return ResponseEntity.ok("Đăng xuất thành công!");
    }

}