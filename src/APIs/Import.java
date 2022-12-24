package APIs;

import DBConnection.DBConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Encryption.Encryptable;
import Encryption.FileNameEncryptor;
import Encryption.FileNameEncryptorAdapter;
import DBConnection.DBConnection;
import DBConnection.CloseConnection;
import FileRepositroy.GetType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

public class Import {
    public static void main(String[] args) throws SQLException, IOException {
            Connection connection;
            File file=new File("C:\\Users\\citycomp\\Desktop\\test.txt");
            connection = DBConnection.getInstance().getConnection();
            Encryptable encryptable = new FileNameEncryptorAdapter(new FileNameEncryptor());
            String encryptedFileName = encryptable.encrypt(file.getName());
            String query = "SELECT fileVersion FROM fields WHERE fileName = ?";
            int version = 0;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, file.getName());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    version = rs.getInt(1) + 1;
                }
                String sql = "INSERT INTO fields ( fileName, filePath, fileType,fileSize, fileVersion, fileData,encryptedFile) VALUES ( ?, ?, ?, ?, ?, ?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, file.getName());
                preparedStatement.setString(2, file.getPath());
                preparedStatement.setString(3, GetType.getType(file));
                preparedStatement.setLong(4, file.length());
                preparedStatement.setInt(5, version);
                preparedStatement.setBinaryStream(6, Files.newInputStream(file.toPath()));
                preparedStatement.setString(7, encryptedFileName);
                preparedStatement.executeUpdate();
            } finally {
                CloseConnection.closeConnection();
            }
        }
    }

