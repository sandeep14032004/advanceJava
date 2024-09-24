
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class First {

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

                // insert values into table

                // String s = "insert into student values(13,'uma',101)";
                // Statement stmt = con.createStatement();
                // int n = stmt.executeUpdate(s); //no of rows affected
                // if( n != 0) {
                // System.out.println("Record added");
                // }
                // else {
                // System.out.println("not found");
                // }
                //

                // delete data
                // String s1 = "delete from student where roll = 13";
                // Statement stmt = con.createStatement();
                // int n = stmt.executeUpdate(s1);
                // if(n!=0) {
                // System.out.println("Record deleted");
                // }
                // else {
                // System.out.println("not deleted");
                // }
                //
                //
                //
                // show table

                String s = "select * from student";
                Statement stmt = con.createStatement();
                // if we change any values in database we uses executeUpdate() if not then
                // executeQuery()
                ResultSet rs = stmt.executeQuery(s);
                // there is a marker that is at the above of the table so we have to bring it
                // to the data so we use .next() method
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
                }
                // next method returns false when the cursor reach to end

                // update
                // String s3 = "update student set mark = 98 where roll=12";
                // Statement stmt = con.createStatement();
                // int n2 = stmt.executeUpdate(s3);
                // if(n2!=0) {
                // System.out.println("updated");
                // }
                // else {
                // System.out.println("not updated");
                // }

            } else {
                System.out.println("Not connectd");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
