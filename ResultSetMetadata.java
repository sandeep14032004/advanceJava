import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ResultSetMetadata {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            if (con != null) {
                String s = "SELECT * FROM student";

                stmt = con.createStatement();

                rs = stmt.executeQuery(s);

                ResultSetMetaData rsmd = rs.getMetaData();

                int noc = rsmd.getColumnCount();
                System.out.println("Number of Columns: " + noc);

                for (int i = 1; i <= noc; i++) {
                    System.out.println("Column " + i + " = " + rsmd.getColumnName(i));
                    System.out.println("Column Type " + i + " = " + rsmd.getColumnType(i));
                    System.out.println("Column Type Name " + i + " = " + rsmd.getColumnTypeName(i));
                }

                // Print the ResultSet rows (data from student table)
                System.out.println("\nData from student table:");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
                }
            } else {
                System.out.println("Connection not established.");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
