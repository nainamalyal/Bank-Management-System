package Console_based_JDBC_Project;

//Creating a console-based project using JDBC involves setting up a
//Java application to interact with a database, 
//performing operations like creating, reading, updating, and deleting (CRUD) data. 
//For Bank Managment create this simple project

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BankManagement {

    private static final String URL = "jdbc:mysql://localhost:3306/iims";
    private static final String USER = "root";
    private static final String PASSWORD = "N#1803@nmm";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();

            while (true) {
                System.out.println("\n====== BANK MANAGEMENT SYSTEM ======");
                System.out.println("1. Create Account");
                System.out.println("2. View Accounts");
                System.out.println("3. Deposit Money");
                System.out.println("4. Withdraw Money");
                System.out.println("5. Delete Account");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Account No: ");
                        int acc = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Balance: ");
                        double bal = sc.nextDouble();

                        stmt.executeUpdate("INSERT INTO accounts VALUES(" + acc + ",'" + name + "'," + bal + ")");
                        System.out.println("Account Created Successfully!");
                        break;

                    case 2:
                        ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");
                        System.out.println("\n--- Account Details ---");
                        while (rs.next()) {
                            System.out.println("Acc No: " + rs.getInt(1) +
                                    " | Name: " + rs.getString(2) +
                                    " | Balance: " + rs.getDouble(3));
                        }
                        break;

                    case 3:
                        System.out.print("Enter Account No: ");
                        int accDeposit = sc.nextInt();
                        System.out.print("Enter Amount to Deposit: ");
                        double amtDeposit = sc.nextDouble();

                        stmt.executeUpdate("UPDATE accounts SET balance = balance + " + amtDeposit + " WHERE acc_no=" + accDeposit);
                        System.out.println("Money Deposited Susscessfully!");
                        break;

                    case 4:
                        System.out.print("Enter Account No: ");
                        int accWithdraw = sc.nextInt();
                        System.out.print("Enter Amount to Withdraw: ");
                        double amtWithdraw = sc.nextDouble();

                        stmt.executeUpdate("UPDATE accounts SET balance = balance - " + amtWithdraw + " WHERE acc_no=" + accWithdraw);
                        System.out.println("Money Withdrawal!");
                        break;

                    case 5:
                        System.out.print("Enter Account No to Delete: ");
                        int accDelete = sc.nextInt();

                        stmt.executeUpdate("DELETE FROM accounts WHERE acc_no=" + accDelete);
                        System.out.println("Account Deleted Susscessfully!");
                        break;

                    case 6:
                        System.out.println("Thank you....Exiting!");
                        con.close();
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid Choice, Try Again!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
