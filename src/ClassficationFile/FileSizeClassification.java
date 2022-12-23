package ClassficationFile;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FileSizeClassification  implements IclassificationTypeAndSize{
    public static Connection connection;
    final static String FILE_SIZE="fileSize";
    @Override
    public void classify() {
        String fileSize ="";
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM fields ORDER BY fileSize";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                fileSize = resultSet.getString(FILE_SIZE);
                System.out.println(fileSize);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



