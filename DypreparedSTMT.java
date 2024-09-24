import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.Scanner;

public class DypreparedSTMT {

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
                Scanner sc = new Scanner(System.in);

                while (true) {
                    System.out.println("\nChoose an option:");
                    System.out.println("1. Insert Data");
                    System.out.println("2. Update Data");
                    System.out.println("3. Delete Data");
                    System.out.println("4. Show Table");
                    System.out.println("5. Exit");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Enter Roll No: ");
                            int roll = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter Name: ");
                            String name = sc.nextLine();
                            System.out.println("Enter Marks: ");
                            int marks = sc.nextInt();

                            String insertSQL = "INSERT INTO student VALUES (?, ?, ?)";
                            PreparedStatement pstmtInsert = con.prepareStatement(insertSQL);
                            pstmtInsert.setInt(1, roll);
                            pstmtInsert.setString(2, name);
                            pstmtInsert.setInt(3, marks);

                            int rowsInserted = pstmtInsert.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("Record inserted successfully.");
                            } else {
                                System.out.println("Insertion failed.");
                            }
                            break;

                        case 2:
                            System.out.println("Enter Roll No of the student to update: ");
                            int updateRoll = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Choose field to update:");
                            System.out.println("1. Update Name");
                            System.out.println("2. Update Marks");
                            int updateChoice = sc.nextInt();
                            sc.nextLine();

                            if (updateChoice == 1) {
                                System.out.println("Enter new name: ");
                                String newName = sc.nextLine();

                                String updateNameSQL = "UPDATE student SET name = ? WHERE roll = ?";
                                PreparedStatement pstmtUpdateName = con.prepareStatement(updateNameSQL);
                                pstmtUpdateName.setString(1, newName);
                                pstmtUpdateName.setInt(2, updateRoll);

                                int rowsUpdatedName = pstmtUpdateName.executeUpdate();
                                if (rowsUpdatedName > 0) {
                                    System.out.println("Name updated successfully.");
                                } else {
                                    System.out.println("Update failed.");
                                }
                            } else if (updateChoice == 2) {
                                System.out.println("Enter new marks: ");
                                int newMarks = sc.nextInt();

                                String updateMarksSQL = "UPDATE student SET mark = ? WHERE roll = ?";
                                PreparedStatement pstmtUpdateMarks = con.prepareStatement(updateMarksSQL);
                                pstmtUpdateMarks.setInt(1, newMarks);
                                pstmtUpdateMarks.setInt(2, updateRoll);

                                int rowsUpdatedMarks = pstmtUpdateMarks.executeUpdate();
                                if (rowsUpdatedMarks > 0) {
                                    System.out.println("Marks updated successfully.");
                                } else {
                                    System.out.println("Update failed.");
                                }
                            } else {
                                System.out.println("Invalid choice.");
                            }
                            break;

                        case 3:
                            System.out.println("Enter Roll No to delete: ");
                            int deleteRoll = sc.nextInt();

                            String deleteSQL = "DELETE FROM student WHERE roll = ?";
                            PreparedStatement pstmtDelete = con.prepareStatement(deleteSQL);
                            pstmtDelete.setInt(1, deleteRoll);

                            int rowsDeleted = pstmtDelete.executeUpdate();
                            if (rowsDeleted > 0) {
                                System.out.println("Record deleted successfully.");
                            } else {
                                System.out.println("Deletion failed.");
                            }
                            break;

                        case 4:
                            String selectSQL = "SELECT * FROM student";
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(selectSQL);

                            while (rs.next()) {
                                System.out.println("roll : " + rs.getInt(1) + " name: " + rs.getString(2) + " marks: "
                                        + rs.getInt(3));
                            }
                            break;

                        case 5:
                            System.out.println("Exiting...");
                            sc.close();
                            con.close();
                            return;

                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }

            } else {
                System.out.println("Connection failed.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
