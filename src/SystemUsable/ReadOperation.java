package SystemUsable;

import DBConnection.DBConnection;
import  DBConnection.CloseConnection;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static DBConnection.DBConnection.connection;

public class ReadOperation implements Operation {
    private static final Logger logger = Logger.getLogger( ReadOperation.class.getName());
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    InputStream inputStream = null;
    final static private  int ID_PARAMETER =1;
    private static final String CLOSING_DATABASE ="Closing database connection";
    @Override
    public void execute(File file) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet= Query(file.getName());
            if (resultSet.next()) {
                inputStream = resultSet.getBinaryStream("fileData");
                System.out.println("File "+file.getName()+" allowed Read");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection();
            logger.info(CLOSING_DATABASE);
        }
    }
    public ResultSet Query(String filename) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT fileData  FROM fields WHERE fileName = ?");
        preparedStatement.setString(ID_PARAMETER,filename );
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


}