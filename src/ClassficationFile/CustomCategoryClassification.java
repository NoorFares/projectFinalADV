package ClassficationFile;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomCategoryClassification implements FileClassificationCustom{
    public static Connection connection;
    static final int PARAMETER_INDEX =1;
    @Override
    public void classify(String category ) {
        try {
            connection = DBConnection.getInstance().getConnection();
                String sql = "SELECT * FROM fields ORDER BY ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(PARAMETER_INDEX, category);
                ResultSet resultSet = preparedStatement.executeQuery();
               while (resultSet.next()) {
                String fileType = resultSet.getString(category);
                System.out.println(fileType);
             }
            connection.close();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }
    }

