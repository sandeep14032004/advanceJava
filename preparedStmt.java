import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.sql.*;

public class preparedStmt {

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if (con != null) {
                String s1 = "insert into student values (?,?,?)";
                PreparedStatement pstmt = con.prepareStatement(s1);
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Roll No: ");
                int roll = sc.nextInt();
                System.out.println("Enter Name: ");
                String name = sc.next();
                System.out.println("Enter Mark: ");
                int mark = sc.nextInt();
                pstmt.setInt(1, roll);
                pstmt.setString(2, name);
                pstmt.setInt(3, mark);
                pstmt.executeUpdate();
                String s = "select * from student";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(s);
                sc.close();
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
                }
            } else {
                System.out.println("not connected");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}