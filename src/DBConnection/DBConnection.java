package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    public   static Connection connection;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final  String URL_DB="jdbc:mysql://localhost/filemangmentsystemm";
    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL_DB, USER_NAME, PASSWORD);

        }
        return connection;
    }
}
