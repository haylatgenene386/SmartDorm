
package com.smartdorm.ui;

import java.util.Scanner;

import com.smartdorm.model.Dormitory;
import com.smartdorm.service.DormitoryReportService;

public class DormitoryReportMenu {

    private Dormitory dormitory;
    private DormitoryReportService reportService;
    private Scanner scanner;

    // Constructor
    public DormitoryReportMenu(Dormitory dormitory) {

        if (dormitory == null) {
            throw new IllegalArgumentException(
                    "Dormitory cannot be null.");
        }

        this.dormitory = dormitory;
        this.reportService = new DormitoryReportService();
        this.scanner = new Scanner(System.in);
    }

    // Display dormitory report menu
    public void displayMenu() {

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n================================");
            System.out.println("       DORMITORY REPORTS");
            System.out.println("================================");
            System.out.println("1. Overall Occupancy Report");
            System.out.println("2. Male Section Report");
            System.out.println("3. Female Section Report");
            System.out.println("4. Building Occupancy Reports");
            System.out.println("5. Room Occupancy Summary");
            System.out.println("0. Return to Main Menu");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println(
                        "Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    reportService.displayOverallReport(dormitory);
                    break;

                case 2:
                    reportService.displaySectionReport(
                            dormitory.getMaleSection());
                    break;

                case 3:
                    reportService.displaySectionReport(
                            dormitory.getFemaleSection());
                    break;

                case 4:
                    reportService.displayBuildingReports(dormitory);
                    break;

                case 5:
                    reportService.displayRoomOccupancySummary(
                            dormitory);
                    break;

                case 0:
                    System.out.println(
                            "Returning to Main Menu...");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }
}
