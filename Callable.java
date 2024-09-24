import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Callable {

    public static void main(String[] args) {
        CallableStatement cstmt = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if (con != null) {
                cstmt = con.prepareCall("{?=call add_num(?,?)}");
                cstmt.setInt(2, 50);
                cstmt.setInt(3, 90);
                cstmt.registerOutParameter(1, Types.INTEGER);
                cstmt.execute();
                int result = cstmt.getInt(1);
                System.out.println("Result: " + result);
            } else {
                System.out.println("Not connected");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing CallableStatement: " + e.getMessage());
            }
        }
    }
}
