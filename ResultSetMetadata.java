import java.sql.*;

public class ResultSetMetadata {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajava", "root", "sandeep1818");

            if (con != null) {
                // SQL query
                String s = "SELECT * FROM student";

                // Create statement
                stmt = con.createStatement();

                // Execute query
                rs = stmt.executeQuery(s);

                // Get metadata of the result set
                ResultSetMetaData rsmd = rs.getMetaData();

                // Get number of columns
                int noc = rsmd.getColumnCount();
                System.out.println("Number of Columns: " + noc);

                // Print column names, types, and type names
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
