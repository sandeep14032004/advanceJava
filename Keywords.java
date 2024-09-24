import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Keywords {
    public static void main(String[] args) {
        Connection con = null; // Declare the connection outside of the try block

        try {
            // Load database properties from config.properties file
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            // Get connection details from properties
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            // Establish the connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Get metadata and retrieve SQL keywords supported by the database
            DatabaseMetaData dbMetaData = con.getMetaData();
            String sqlKeywords = dbMetaData.getSQLKeywords();

            // Print the SQL keywords
            System.out.println("SQL Keywords supported by the database: " + sqlKeywords);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
