import SystemUsable.ImportOperation;
import SystemUsable.Operation;
import SystemUsable.Rollback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        File file = new File("C:\\Users\\citycomp\\Desktop\\test.txt");
        System.out.println("Hello world!");
        Operation operation = new Rollback();
        //operation.execute(file);
        Operation operation1 = new ImportOperation();
        operation1.execute(file);
    }
}