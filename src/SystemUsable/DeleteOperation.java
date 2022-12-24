package SystemUsable;
import DBConnection.DBConnection;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DBConnection.CloseConnection;
import java.util.logging.Logger;
public class DeleteOperation implements Operation {
    private static final Logger logger = Logger.getLogger(DeleteOperation.class.getName());
    final static private  int ID_PARAMETER =1;
    private static final String CLOSING_DATABASE ="Closing database connection";
    @Override
    public void execute(File file) {
        String sql = "DELETE FROM fields WHERE fileName = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(ID_PARAMETER, file.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }finally {
            CloseConnection.closeConnection();
            logger.info(CLOSING_DATABASE);
        }

    }
}