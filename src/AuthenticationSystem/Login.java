package AuthenticationSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    private static final String ENTER_USERNAME ="Enter username: ";
    private static final String ENTER_PASSWORD ="Enter password: ";
    private static final String LOGIN_MESSAGE ="Logged in successfully";
    private static final String LOGIN_MESSAGE_INVALID ="Invalid username or password";

    public static void login(Statement statement) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(ENTER_USERNAME);
            String username = sc.nextLine();
            System.out.print(ENTER_PASSWORD);
            String password = sc.nextLine();

            String sql = "SELECT * FROM authentication WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println(LOGIN_MESSAGE);
            } else {
                System.out.println(LOGIN_MESSAGE_INVALID);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
