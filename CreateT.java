
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class CreateT {
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
            String sql = "CREATE TABLE Students (" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(100), " +
                    "age INT, " +
                    "PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Table create successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
