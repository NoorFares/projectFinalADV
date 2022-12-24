package SystemUsable;

import DBConnection.DBConnection;
import DBConnection.CloseConnection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import static DBConnection.DBConnection.connection;

public class Rollback implements Operation{
    private static final Logger logger = Logger.getLogger(Rollback.class.getName());
    private static final String LATEST_VERSION ="The last version of this File: ";
    private static final String WANTED_VERSION ="Enter the version you needed it: ";
    private static final String ROLLBACK_MESSAGE ="Rollback message: operation executed ";
    private static final int FILE_NAME_INDEX =1;
    private static final int FILE_VERSION_RETRIEVE_INDEX =1;
    private static final int FILE_VERSION_DELETE_INDEX =2;
    private static final String CLOSING_DATABASE ="Closing database connection";

    @Override
    public void execute(File file) {
        try{
        logger.info(ROLLBACK_MESSAGE);
        retrieveLatestVersion(file);
        deleteVersion(file);
        }catch (SQLException e){
            e.printStackTrace();
            CloseConnection.closeConnection();
            logger.info(CLOSING_DATABASE);
        }
    }

    public static int retrieveLatestVersion(File file) throws SQLException {
        connection = DBConnection.getInstance().getConnection();
        String selectQuery = "SELECT MAX(fileVersion) FROM fields WHERE fileName = ?";
        PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
        selectStmt.setString(FILE_NAME_INDEX, file.getName());
        ResultSet rs = selectStmt.executeQuery();
        int maxVersion = 0;
        if (rs.next()) {
            maxVersion = rs.getInt(FILE_VERSION_RETRIEVE_INDEX);
            System.out.println(LATEST_VERSION +maxVersion);
        }
        return maxVersion;
    }
    public static void deleteVersion(File file) throws SQLException {
        connection = DBConnection.getInstance().getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println(WANTED_VERSION);
        int version = sc.nextInt();
        String deleteQuery = "DELETE FROM fields WHERE fileName = ? AND fileVersion != ?";
        PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
        deleteStmt.setString(FILE_NAME_INDEX, file.getName());
        deleteStmt.setInt(FILE_VERSION_DELETE_INDEX, version);
        deleteStmt.executeUpdate();
    }
}