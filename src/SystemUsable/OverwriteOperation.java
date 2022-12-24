package SystemUsable;
import AuthenticationSystem.Authentication;
import DBConnection.DBConnection;
import DBConnection.CloseConnection;
import Encryption.Encryptable;
import Encryption.FileNameEncryptor;
import Encryption.FileNameEncryptorAdapter;
import FileRepositroy.GetType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class OverwriteOperation implements Operation{
    private static final Logger logger = Logger.getLogger(OverwriteOperation.class.getName());
    private static final int FILE_NAME_INDEX =1;
    private static final int FILE_NAME_INDEX_FOR_UPDATE =2;
    private static final int FILE_PATH_INDEX =2;
    private static final int FILE_TYPE_INDEX =3;
    private static final int FILE_LENGTH_INDEX =4;
    private static final int FILE_DATA_INDEX =5;
    private static final int FILE_ENCRYPTED_INDEX =6;
    private static final String OVERWRITE_OPERATION ="Overwrite operation";
    private static final String CLOSING_DATABASE ="Closing database connection";

    @Override
    public void execute(File file) {
        Connection conn = null;
        try {
            logger.info(OVERWRITE_OPERATION);
            conn = DBConnection.getInstance().getConnection();
            InputStream inputStream = new FileInputStream(file); Encryptable encryptable = new FileNameEncryptorAdapter(new FileNameEncryptor());
            String encryptedFileName = encryptable.encrypt(file.getName());
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM fields WHERE fileName = ?");
            pstmt.setString(FILE_NAME_INDEX, file.getName());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                updateFile(conn, file.getName(),inputStream);
            } else {
                insertFile(conn,file,inputStream,encryptedFileName);
            }
        } catch (SQLException | FileNotFoundException e) {
            System.out.println(e.getMessage());

        } finally {
            CloseConnection.closeConnection();
            logger.info(CLOSING_DATABASE);
        }
    }

    public static void updateFile(Connection conn, String fileName, InputStream inputStream) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("UPDATE fields SET fileData = ?, fileVersion = fileVersion + 1 WHERE fileName = ?");
        pstmt.setBinaryStream(FILE_DATA_INDEX, inputStream);
        pstmt.setString(FILE_NAME_INDEX_FOR_UPDATE, fileName);
        pstmt.executeUpdate();
    }

    public static void insertFile(Connection conn, File file,InputStream inputStream,String encryptedFileName) throws SQLException {

        String sql ="INSERT INTO fields( fileName, filePath, fileType,fileSize, fileVersion, fileData,encryptedFile) VALUES (?, ?, ?, ?, 1, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(FILE_NAME_INDEX, file.getName());
        pstmt.setString(FILE_PATH_INDEX, file.getPath());
        pstmt.setString(FILE_TYPE_INDEX, GetType.getType(file));
        pstmt.setLong(FILE_LENGTH_INDEX, file.length());
        pstmt.setBinaryStream(FILE_DATA_INDEX, inputStream);
        pstmt.setString(FILE_ENCRYPTED_INDEX, encryptedFileName);
        pstmt.executeUpdate();
    }
}

