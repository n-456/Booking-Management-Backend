package shop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.dao.CustomerDAO;
import shop.model.Customer;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true") // credentails

public class AuthController {

    private final CustomerDAO customerDAO;

    public AuthController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer loginRequest, HttpServletRequest request) {
        // Kiểm tra thông tin trong database
        Customer customer = customerDAO.checkLogin(loginRequest.getEmail(), loginRequest.getPass());

        if (customer != null) {
            // Tạo session và lưu thông tin user vào session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", customer);

            return ResponseEntity.ok("Đăng nhập thành công!");
        } else {
            return ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Lấy session hiện tại nhưng không tạo mới (nếu có)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Hủy bỏ session
        }
        return ResponseEntity.ok("Đăng xuất thành công!");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        // Lấy session hiện tại
        HttpSession session = request.getSession(false);

        // Kiểm tra xem đã đăng nhập chưa
        if (session == null || session.getAttribute("currentUser") == null) {
            return ResponseEntity.status(401).body("Bạn chưa đăng nhập!");
        }

        // Lấy thông tin user từ session ra dùng
        Customer customer = (Customer) session.getAttribute("currentUser");
        return ResponseEntity.ok(customer);
    }
}