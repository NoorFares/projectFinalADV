package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    public   static Connection connection;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final  String URL_DB="jdbc:mysql://localhost/filemangmentsystemm";
    private DBConnection() {
        // Private constructor to prevent direct instantiation
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            // Create the connection
            connection = DriverManager.getConnection(URL_DB, USER_NAME, PASSWORD);

        }
        return connection;
    }
}
/*CREATE TABLE `filemangmentsystemm`.`fields` ( `fileNumber` INT(10) NULL AUTO_INCREMENT , `fileName` VARCHAR(255) NOT NULL , `filePath` VARCHAR(255) NOT NULL , `fileType` VARCHAR(255) NOT NULL , `fileSize` BIT(64) NOT NULL , `fileVersion` INT(10) NOT NULL , `fileData` LONGBLOB NOT NULL , `previous_version` INT(10) NOT NULL , `encryptedFile` VARCHAR(255) NOT NULL , PRIMARY KEY (`fileNumber`)) ENGINE = InnoDB;
*/