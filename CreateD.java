import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class CreateD {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection con = null;

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            stmt = con.createStatement();
            String sql = "CREATE DATABASE newone";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully");

        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
