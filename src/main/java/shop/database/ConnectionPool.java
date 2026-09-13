package shop.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConnectionPool {

    private static HikariDataSource dataSource;

    static {
        try {
            // Đọc cấu hình
            Properties props = new Properties();
            InputStream input = ConnectionPool.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Cấu hình HikariCP
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.setMaximumPoolSize(10);        // Max connections
            config.setMinimumIdle(5);             // Min connections
            config.setConnectionTimeout(30000);   // 30 seconds
            config.setIdleTimeout(600000);        // 10 minutes
            config.setMaxLifetime(1800000);       // 30 minutes

            dataSource = new HikariDataSource(config);
            System.out.println("✓ HikariCP Connection Pool initialized");

        } catch (Exception e) {
            System.out.println("✗ Error initializing connection pool: "
                    + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lấy connection từ pool
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Connection pool not initialized");
        }
        return dataSource.getConnection();
    }

    // Đóng pool
    public static void close() {
        if (dataSource != null) {
            dataSource.close();
            System.out.println("✓ Connection pool closed");
        }
    }
}