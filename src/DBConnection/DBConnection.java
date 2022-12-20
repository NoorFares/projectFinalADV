package DBConnection;
import java.io.FileInputStream;
import java.sql.*;
//SOILD principle => single Responsibility
public class DBConnection {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private  static   Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/filerepos", USER_NAME, PASSWORD);
            Statement statement =  connection.createStatement();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}

