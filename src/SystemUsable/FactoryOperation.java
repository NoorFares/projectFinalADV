package SystemUsable;

import AuthenticationSystem.Authentication;
import SystemUsable.User.User;

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


    public  static Operation createObject(String operation, User user) {
        if (user == SystemUsable.User.User.ADMIN) {
            adminOperation(operation);
            logger.info(ADMIN_MESSAGE);
        } else if (user == SystemUsable.User.User.STAFF) {
            StaffOperation(operation);
            logger.info(STAFF_MESSAGE);
        } else if (user == SystemUsable.User.User.READER) {
            return (Operation) new ReadOpreation();
        } else {
            System.out.println(INVALID_ROLE);
        }
        throw new IllegalArgumentException(INVALID_OPERATION);
    }

    public static Operation adminOperation(String operation){

        switch (operation) {
            case IMPORT:
                return (Operation) new ImportOperation();
            case EXPORT:
                return (Operation) new ExportOperation();
            case DELETE:
                return (Operation) new DeleteOperation();
            case READ:
                return (Operation) new ReadOpreation();
            case OVERWRITE:
                return (Operation) new OverwriteOperation();
            case ROLLBACK:
                return (Operation) new Rollback();
        }
        throw new IllegalArgumentException(INVALID_OPERATION);

    }

    public static Operation StaffOperation(String operation){
        switch (operation) {
            case IMPORT:
                return (Operation) new ImportOperation();
            case EXPORT:
                return (Operation) new ExportOperation();
        }
        throw new IllegalArgumentException(INVALID_OPERATION);
    }
}
