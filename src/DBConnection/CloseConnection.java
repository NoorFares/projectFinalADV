package DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseConnection {
    public static void closeConnection(Connection connection, Statement statement){
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
