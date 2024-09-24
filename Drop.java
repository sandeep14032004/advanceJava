
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Drop {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            stmt = con.createStatement();
            String mysql = "drop database newone";
            stmt.executeUpdate(mysql);
            System.out.println("success");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}