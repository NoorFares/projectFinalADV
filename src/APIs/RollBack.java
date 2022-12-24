package APIs;

import DBConnection.DBConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class RollBack {

    private static final Logger logger = Logger.getLogger(RollBack.class.getName());
    public static void main(String[] args) {

        try {
            logger.info("Roll back to a previous file version form Database: ");
            Connection connection;
            connection = DBConnection.getConnection();
            File file = new File("C:\\Users\\citycomp\\Desktop\\test.txt");

            String sql = "SELECT fileVersion, fileName FROM fields WHERE fileName = ? ORDER BY fileVersion DESC LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, file.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int fileVersion = rs.getInt("fileVersion");
                String fileName = rs.getString("fileName");
                System.out.println("Latest version of " + fileName + ": " + fileVersion);
            }
            //String sql = "SELECT * FROM fields WHERE fileName = ? AND fileVersion = ?";
            /*PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, file.getName());
            stmt.setInt(2, 1);
            ResultSet rs = stmt.executeQuery();*/

            // Check if the file was found
           /* while (!rs.next()) {
                throw new FileNotFoundException("File not found in database");
            }*/
            // Delete the current version of the file
            /*stmt = connection.prepareStatement("DELETE FROM fields WHERE fileName = ? AND fileVersion = ?");
            stmt.setString(1, file.getName());
            stmt.setInt(2, 2);
            stmt.executeUpdate();*/

            // Retrieve the previous version of the file
            //int previousVersion = rs.getInt("previous_version");

            // Update the current version of the file with the previous version
            /*stmt = connection.prepareStatement("UPDATE fields SET FileData = ? WHERE fileName = ? AND fileVersion = ?");
            stmt.setBlob(1, rs.getBlob("FileData"));
            stmt.setString(2, file.getName());
            stmt.setInt(3, 1);
            stmt.executeUpdate();

            // Update the version and previous version fields
            stmt = connection.prepareStatement("UPDATE fields SET fileVersion = ?, previous_version = ? WHERE fileName = ?");
            stmt.setInt(1, 0);
            stmt.setInt(2, 1);
            stmt.setString(3, file.getName());
            stmt.executeUpdate();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
