
import java.util.Scanner;

import com.smartdorm.model.Dormitory;
import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.ui.StudentManagementMenu;
import com.smartdorm.ui.RoomManagementMenu;
import com.smartdorm.ui.DormitoryReportMenu;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println(" SMART DORMITORY MANAGEMENT SYSTEM");
        System.out.println("=================================");

        DormitoryConfigurationService configurationService =
                new DormitoryConfigurationService();

        Dormitory dormitory =
                configurationService.configureDormitory();

        System.out.println("\nDormitory configuration completed!");

        boolean running = true;

        while (running) {

            System.out.println("\n=================================");
            System.out.println("          MAIN MENU");
            System.out.println("=================================");
            System.out.println("1. Student Management");
            System.out.println("2. Room Management");
            System.out.println("3. Dormitory Information");
            System.out.println("4. Dormitory Reports");
            System.out.println("0. Exit System");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    StudentManagementMenu studentMenu =
                            new StudentManagementMenu(dormitory);

                    studentMenu.displayMenu();
                    break;

                case "2":

                    RoomManagementMenu roomMenu =
                            new RoomManagementMenu(dormitory);

                    roomMenu.displayMenu();
                    break;

                case "3":

                    dormitory.displayDormitoryInformation();
                    break;

                case "4":

                    DormitoryReportMenu reportMenu =
                            new DormitoryReportMenu(dormitory);

                    reportMenu.displayMenu();
                    break;

                case "0":

                    running = false;

                    System.out.println(
                            "\nThank you for using Smart Dormitory Management System!");
                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
