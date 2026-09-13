package shop.database;

import java.sql.*;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseHelper {

    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String DB_DRIVER;

    static {
        try {
            // Đọc file cấu hình
            Properties props = new Properties();
            InputStream input = DatabaseHelper.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            props.load(input);

            DB_URL = props.getProperty("db.url");
            DB_USER = props.getProperty("db.user");
            DB_PASSWORD = props.getProperty("db.password");
            DB_DRIVER = props.getProperty("db.driver");

            // Load driver
            Class.forName(DB_DRIVER);
            System.out.println("✓ PostgreSQL Driver loaded successfully");

        } catch (Exception e) {
            System.out.println("✗ Error loading database configuration: "
                    + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lấy kết nối mới
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Đóng resources
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }

    // Overload - đóng PreparedStatement
    public static void close(PreparedStatement pstmt, Connection conn) {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }

    // Kiểm tra kết nối
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✓ Database connection successful!");
            return true;
        } catch (SQLException e) {
            System.out.println("✗ Database connection failed: "
                    + e.getMessage());
            return false;
        }
    }
}