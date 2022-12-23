package AuthenticationSystem;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SignUp {
    private static final String ENTER_USERNAME ="Enter username: ";
    private static final String ENTER_PASSWORD ="Enter password: ";
    private static final String SING_UP_MESSAGE ="Signed up successfully";
    private static final String ENTER_ROLE ="Enter your role: ";

    public static void signUp(Statement statement) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print(ENTER_USERNAME);
            String username = sc.nextLine();
            System.out.print(ENTER_PASSWORD);
            String password = sc.nextLine();
            System.out.print(ENTER_ROLE);
            String role = sc.nextLine();

            String sql = "INSERT INTO authentication (username, password, role) VALUES ('" + username + "', '" + password + "' ,'" + role + "')";
            statement.executeUpdate(sql);
            System.out.println(SING_UP_MESSAGE);
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
