package SystemUsable;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface Operation {

    void execute(File file) throws SQLException, IOException;

}

