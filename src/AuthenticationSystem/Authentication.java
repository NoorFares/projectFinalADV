package AuthenticationSystem;

import DBConnection.DBConnection;
import DBConnection.CloseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

public class Authentication {

    private static final Logger logger = Logger.getLogger(Authentication.class.getName());
    private static final int LOGIN =1;
    private static final int SIGN_UP =2;
    private static final int EXIT =3;
    private static final String LOGIN_CHOICE ="1. Login";
    private static final String SIGN_UP_CHOICE ="2. Sign up";
    private static final String EXIT_CHOICE ="3. Exit";
    private static final String ENTER_CHOICE ="Enter choice: ";
    private static final String AUTHENTICATION ="Authentication";
    private static final String INVALID_CHOICE ="Invalid choice";
    private static final String CLOSEING_DATABASE ="Closing database connection";

    public static void authentication(Connection connection, Statement statement) {

        Scanner sc = new Scanner(System.in);
        try {
            connection= DBConnection.getConnection();
            statement = connection.createStatement();
            logger.info(AUTHENTICATION);

            while (true) {
                System.out.println(LOGIN_CHOICE);
                System.out.println(SIGN_UP_CHOICE);
                System.out.println(EXIT_CHOICE);
                System.out.print(ENTER_CHOICE);
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == LOGIN) {
                    Login.login(statement);
                } else if (choice == SIGN_UP) {
                   SignUp.signUp(statement);
                } else if (choice == EXIT) {
                    break;
                } else {
                    System.out.println(INVALID_CHOICE);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(connection, statement);
            logger.info(CLOSEING_DATABASE);
        }
    }
}
