package ClassficationFile;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FileTypeClassification implements IclassificationTypeAndSize{
     Connection connection;
    final static String FILE_TYPE="fileType";
    @Override
    public void classify() {
        String fileType="";
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM fields ORDER BY fileType";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                fileType = resultSet.getString(FILE_TYPE);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
