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

    private static final String LOGIN_CHOICE ="1. Login";
    private static final String SIGN_UP_CHOICE ="2. Sign up";
    private static final String EXIT_CHOICE ="3. Exit";
    private static final String ENTER_CHOICE ="Enter choice: ";
    private static final String INVALID_CHOICE ="Invalid choice";
    private static final String AUTHENTICATION ="Authentication";
    private static final String DO_LOGIN ="Logging in to the system";
    private static final String DO_SIGN_UP ="Signing up to the system";
    private static final String CLOSING_DATABASE ="Closing database connection";

    public static void authentication(Connection connection, Statement statement) {

        Scanner sc = new Scanner(System.in);
        try {
            connection= DBConnection.getConnection();
            statement = connection.createStatement();
            logger.info(AUTHENTICATION);

            System.out.println(LOGIN_CHOICE);
            System.out.println(SIGN_UP_CHOICE);
            System.out.println(EXIT_CHOICE);
            System.out.print(ENTER_CHOICE);
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    Login.login(statement);
                    logger.info(DO_LOGIN);
                    break;
                case 2:
                    SignUp.signUp(statement);
                    logger.info(DO_SIGN_UP);
                    break;
                case 3:
                    logger.info(EXIT_CHOICE);
                    break;
                default:
                    System.out.println(INVALID_CHOICE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            CloseConnection.closeConnection();
            logger.info(CLOSING_DATABASE);
        }
    }
}
