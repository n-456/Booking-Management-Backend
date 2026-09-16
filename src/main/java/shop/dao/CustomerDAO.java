package shop.dao;

import org.springframework.stereotype.Repository;
import shop.database.DatabaseHelper;
import shop.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAO {

    public CustomerDAO() {}

    // CREATE - Tạo bảng
    public void createTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS customers (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "phone VARCHAR(20) UNIQUE NOT NULL," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "pass VARCHAR(100) UNIQUE NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";

            stmt.execute(sql);
            System.out.println("✓ Table 'customers' created successfully!");

        } catch (SQLException e) {
            System.out.println("✗ Error creating table: " + e.getMessage());
        } finally {
            DatabaseHelper.close(null, stmt, conn);
        }
    }

    // INSERT - Thêm customer mới
    public boolean addCustomer(String name, String phone, String email, String pass) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "INSERT INTO customers (name, phone, email, pass) VALUES (?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, pass);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✓ Customer added successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("✗ Error inserting customer: " + e.getMessage());
        } finally {
            DatabaseHelper.close(pstmt, conn);
        }

        return false;
    }

    // SELECT - Lấy tất cả customer
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseHelper.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM customers ORDER BY id DESC");

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                customers.add(customer);
            }

            System.out.println("✓ Retrieved " + customers.size() + " customers");

        } catch (SQLException e) {
            System.out.println("✗ Error retrieving customers: " + e.getMessage());
        } finally {
            DatabaseHelper.close(rs, stmt, conn);
        }

        return customers;
    }

    // SELECT - Lấy customer theo ID
    public Customer getCustomerById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT * FROM customers WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            System.out.println("✗ Error retrieving customer: " + e.getMessage());
        } finally {
            DatabaseHelper.close(rs, pstmt, conn);
        }

        return null;
    }

    // UPDATE - Cập nhật customer
    public boolean updateCustomer(int id, String name, String phone, String email, String pass) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "UPDATE customers " +
                    "SET name = COALESCE(NULLIF(?, ''), name), " +
                    "   phone = COALESCE(NULLIF(?, ''), phone), " +
                    "   email = COALESCE(NULLIF(?, ''), email), " +
                    "   pass = COALESCE(NULLIF(?, ''), pass) " +
                    "WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, pass);
            pstmt.setInt(5, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✓ Customer updated successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("✗ Error updating customer: " + e.getMessage());
        } finally {
            DatabaseHelper.close(pstmt, conn);
        }

        return false;
    }

    // DELETE - Xóa customer
    public boolean deleteCustomer(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "DELETE FROM customers WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✓ Customer deleted successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("✗ Error deleting customer: " + e.getMessage());
        } finally {
            DatabaseHelper.close(pstmt, conn);
        }

        return false;
    }

    public Customer checkLogin(String email, String pass) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT * " +
                    "FROM customers " +
                    "WHERE email = ? " +
                    "AND pass = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, pass);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println("✗ Error check login: " + e.getMessage());
        } finally {
            DatabaseHelper.close(pstmt, conn);
        }
        return customer;
    }
}