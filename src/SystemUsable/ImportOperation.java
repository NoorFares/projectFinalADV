package SystemUsable;
import AuthenticationSystem.Authentication;
import DBConnection.DBConnection;
import DBConnection.CloseConnection;
import Encryption.Encryptable;
import Encryption.FileNameEncryptor;
import Encryption.FileNameEncryptorAdapter;
import FileRepositroy.GetType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ImportOperation implements Operation {
    private static final Logger logger = Logger.getLogger(ImportOperation.class.getName());
    private static final int FILE_VERSION_RETRIEVE_INDEX =1;
    private static final int ADD_ONE_TO_FILE_VERSION_INDEX =1;
    private static final int FILE_NAME_INDEX =1;
    private static final int FILE_PATH_INDEX =2;
    private static final int FILE_TYPE_INDEX =3;
    private static final int FILE_LENGTH_INDEX =4;
    private static final int FILE_VERSION_INDEX =5;
    private static final int FILE_DATA_INDEX =6;
    private static final int FILE_ENCRYPTED_INDEX =7;
    private static final String IMPORT_MESSAGE ="Import operation: ";
    private static final String IMPORT_MESSAGE_INSERT ="Import operation, Insert file: ";
    private static final String CLOSING_DATABASE ="Closing database connection";

    @Override
    public void execute(File file) {
        Connection connection;
        String query = "SELECT fileVersion FROM fields WHERE fileName = ?";
        try {
            logger.info(IMPORT_MESSAGE);
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(FILE_NAME_INDEX, file.getName());
            ResultSet rs = preparedStatement.executeQuery();
            insertFile(preparedStatement, connection, file, rs);
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection();
            logger.info(CLOSING_DATABASE);
        }
    }

    public static void insertFile(PreparedStatement preparedStatement, Connection connection, File file, ResultSet rs) {
        logger.info(IMPORT_MESSAGE_INSERT);
        Encryptable encryptable = new FileNameEncryptorAdapter(new FileNameEncryptor());
        String encryptedFileName = encryptable.encrypt(file.getName());
        int version = 0;
        try {
            while (rs.next()) {
                version = rs.getInt(FILE_VERSION_RETRIEVE_INDEX) + ADD_ONE_TO_FILE_VERSION_INDEX;
            }
            String sql = "INSERT INTO fields ( fileName, filePath, fileType,fileSize, fileVersion, fileData,encryptedFile) VALUES ( ?, ?, ?, ?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(FILE_NAME_INDEX, file.getName());
            preparedStatement.setString(FILE_PATH_INDEX, file.getPath());
            preparedStatement.setString(FILE_TYPE_INDEX, GetType.getType(file));
            preparedStatement.setLong(FILE_LENGTH_INDEX, file.length());
            preparedStatement.setInt(FILE_VERSION_INDEX, version);
            preparedStatement.setBinaryStream(FILE_DATA_INDEX, Files.newInputStream(file.toPath()));
            preparedStatement.setString(FILE_ENCRYPTED_INDEX, encryptedFileName);
            preparedStatement.executeUpdate();
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
