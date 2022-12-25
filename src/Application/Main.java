package Application;
import SystemUsable.FactoryOperation;
import SystemUsable.Operation;
import SystemUsable.ReadOperation;
import SystemUsable.ReadOpreation;
import SystemUsable.User.User;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
class Main{
public static void main(String[] args) throws SQLException, IOException {
   File file=new File("C:\\Users\\Msys\\Desktop\\sample.json");
    File file1=new File("C:\\Users\\Msys\\Desktop\\Adv.pdf");
    File file2=new File("C:\\Users\\Msys\\Desktop\\noor.txt");
    Scanner input = new Scanner(System.in);
    while (true) {
        System.out.println("Choices User:");
        System.out.println("1. userAdmin");
        System.out.println("2. userStaff");
        System.out.println("3. userReader");
        System.out.println("4. Quit");
        System.out.println("Enter your choice of user Allowed use the System: ");
        int choice = input.nextInt();
        if (choice == 1) {
            menuaAdmin(User.ADMIN,file);
        } else if (choice == 2) {
            menuasStaff(User.STAFF, file1);
        } else if (choice == 3) {
            System.out.println("Reader Read Only files ");
            Operation Read =FactoryOperation.createObject("READ",User.READER,file1);
            Read.execute(file2);
        } else if (choice == 4) {
        break;
        } else {
        System.out.println("Invalid choice. Please try again.");
        }
        }input.close();
        }
    public static void  menuaAdmin(User user, File file) throws SQLException, IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Admin Option  enter 1");
        int choice=scanner.nextInt();
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Import");
            System.out.println("2. Overwrite");
            System.out.println("3. Rollback");
            System.out.println("4. Export");
            System.out.println("5. Delete");
            System.out.println("6. Read");
            System.out.println("7. Exit");
            System.out.println("Enter your selection: ");

            int selection = scanner.nextInt();

            if (selection == 7) {
                break;
            }

            String operation;
            switch (selection) {
                case 1:
                    operation = "IMPORT";
                    break;
                case 2:
                    operation = "OVERWRITE";
                    break;
                case 3:
                    operation = "ROLLBACK";
                    break;
                case 4:
                    operation = "EXPORT";
                    break;
                case 5:
                    operation = "DELETE";
                    break;
                case 6:
                    operation = "READ";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid selection: " + selection);
            }

            FactoryOperation.createObject(operation, user, file);
        }
    }
    public static void menuasStaff(User user,File file) throws SQLException, IOException {
        System.out.println("Staff Option enter 2");
    Scanner scanner=new Scanner(System.in);
    int choice=scanner.nextInt();
    while (true) {
        System.out.println("Menu:");
        System.out.println("1. Import");
        System.out.println("2. Export");
        System.out.println("3. Exit");
        System.out.print("Enter your selection: ");
        int selection = scanner.nextInt();

        if (selection == 3) {
            break;
        }
        String operation;
        switch (selection) {
            case 1:
                operation = "IMPORT";
                break;
            case 2:
                operation = "EXPORT";
                break;
            default:
                throw new IllegalArgumentException("Invalid selection: " + selection);
        }

        FactoryOperation.createObject(operation, user, file);
    }
}
        }