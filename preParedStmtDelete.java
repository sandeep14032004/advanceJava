
import java.sql.*;
import java.util.Scanner;

public class preParedStmtDelete {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajava", "root", "sandeep1818");
            Class.forName("com.mysql.cj.jdbc.Driver");

            Scanner sc = new Scanner(System.in);

            if (con != null) {
                String s1 = "delete from student where roll=?";
                try (PreparedStatement pstmt = con.prepareStatement(s1)) {
                    System.out.println("Enter your roll no : to delete : ");
                    int roll = sc.nextInt();

                    pstmt.setInt(1, roll);

                    pstmt.executeUpdate();
                    System.out.println("::::::::::::deleted successfully::::::::::");
                    sc.close();
                }
                String s = "select * from student";
                try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(s)) {
                    while (rs.next()) {
                        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
                    }
                }
            } else {
                System.out.println("Not connected");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
