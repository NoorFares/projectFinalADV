package SystemUsable;
import DBConnection.DBConnection;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import DBConnection.CloseConnection;
import SystemUsable.Operation;

public class ExportOperation implements Operation {
    private static final Logger logger = Logger.getLogger(ExportOperation.class.getName());
    public ResultSet resultSet = null;
    final static private String FILE_DATA ="FileData";
    final static private String  FILE_PATH_SAVE="C:\\Users\\AL-Aseel\\Downloads\\jjj.txt";
    final static private  int BYTE_READ=-1;
    final static private  int SIZE_BUFFER =4096;
    final static private  int OFF=0;
    final static private int FILE_NAME_INDEX =1;
    final private String FileExportedSuccessfully="File exported successfully";

    private static final String CLOSING_DATABASE ="Closing database connection";
   private static final String EXPORT_INFORMATION="export done";
    @Override
    public void execute(File file) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT fileData FROM fields WHERE fileName = ? ORDER BY fileVersion DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(FILE_NAME_INDEX, file.getName());
            resultSet = preparedStatement.executeQuery();
            readContent();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection();
            logger.info(CLOSING_DATABASE);
        }

    }
    public void readContent() throws SQLException, IOException {
        if (resultSet.next()) {
            InputStream inputStream = resultSet.getBinaryStream(FILE_DATA);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[SIZE_BUFFER];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != BYTE_READ) {
                outputStream.write(buffer, OFF, bytesRead);
            }
            byte[] fileBytes = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH_SAVE);
            fileOutputStream.write(fileBytes);
            System.out.println(outputStream);
            fileOutputStream.close();
            System.out.println(FileExportedSuccessfully);
        }
        logger.info(EXPORT_INFORMATION);
    }
}