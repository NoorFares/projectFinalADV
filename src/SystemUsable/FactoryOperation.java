package SystemUsable;
import AuthenticationSystem.Authentication;
import SystemUsable.User.User;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
public class FactoryOperation {
    private static final Logger logger = Logger.getLogger(FactoryOperation.class.getName());
    private static final String ADMIN_MESSAGE = "Admin create action";
    private static final String STAFF_MESSAGE = "Staff create action";
    private static final String IMPORT="IMPORT";
    private static final String EXPORT="EXPORT";
    private static final String DELETE="DELETE";
    private static final String READ="READ";
    private static final String OVERWRITE="OVERWRITE";
    private static final String  ROLLBACK="ROLLBACK";
    private static final String INVALID_OPERATION="Invalid operation type";
    private static final String INVALID_ROLE="Invalid role type";
    public  static Operation createObject(String operation, User user, File f) throws SQLException, IOException {
        Operation operation1=null;
        if (user == SystemUsable.User.User.ADMIN) {
        operation1= adminOperation(operation);

            logger.info(ADMIN_MESSAGE);return operation1;
        } else if (user == SystemUsable.User.User.STAFF) {
         operation1=   StaffOperation(operation);
            logger.info(STAFF_MESSAGE);
            return operation1;
        } else if (user == SystemUsable.User.User.READER) {
            operation1=new ReadOperation();
            return operation1;
        } else {
            System.out.println(INVALID_ROLE);
        }
        return null;
    }

    public static Operation adminOperation(String operation) throws SQLException, IOException {
    Operation operation1=null;
    File f=new File("C:\\Users\\Msys\\Desktop\\noor.txt");
            switch (operation) {
                case IMPORT:
                    operation1=  new ImportOperation();
                    operation1.execute(f);
                    return operation1;
                case EXPORT:
                   operation1= new ExportOperation();
                operation1.execute(f);
                return operation1;

            case DELETE:
                operation1=  new DeleteOperation();
                operation1.execute(f);
                return operation1;
            case READ:
              operation1= new ReadOperation();
              operation1.execute(f);
              return operation1;
            case OVERWRITE:
                operation1= new OverwriteOperation();
                operation1.execute(f);
                return operation1;

            case ROLLBACK:
               operation1=  new Rollback();
              operation1.execute(f);
                return operation1;
        }
        throw new IllegalArgumentException(INVALID_OPERATION);

    }

    public static Operation StaffOperation(String operation){
        Operation operation1=null;
        File f=new File("C:\\Users\\Msys\\Desktop\\sample.json");
        switch (operation) {
            case IMPORT:
              operation1= new ImportOperation();
              return operation1;
            case EXPORT:
              operation1= new ExportOperation();
              return operation1;
        }
        throw new IllegalArgumentException(INVALID_OPERATION);
    }
}
