
package com.smartdorm.ui;

import java.util.Scanner;

import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Block;
import com.smartdorm.model.Room;

import com.smartdorm.enums.SectionType;

import com.smartdorm.service.RoomManagementService;

public class RoomManagementMenu {

    private static Scanner scanner = new Scanner(System.in);

    private RoomManagementService roomService;
    private Dormitory dormitory;

    // Constructor
    public RoomManagementMenu(Dormitory dormitory) {

        this.dormitory = dormitory;
        roomService = new RoomManagementService();
    }

    // Display room management menu
    public void displayMenu() {

        int choice;

        do {

            System.out.println("\n==================================");
            System.out.println("      ROOM MANAGEMENT SYSTEM");
            System.out.println("==================================");

            System.out.println("1. Display All Buildings");
            System.out.println("2. Display Male Buildings");
            System.out.println("3. Display Female Buildings");
            System.out.println("4. Display Rooms in a Building");
            System.out.println("5. Search Building");
            System.out.println("6. Search Room");
            System.out.println("0. Exit");

            System.out.println("----------------------------------");
            System.out.print("Enter your choice: ");

            choice = readInteger();

            switch (choice) {

                case 1:
                    roomService.displayAllBuildings(dormitory);
                    break;

                case 2:
                    roomService.displayBuildings(
                            dormitory.getMaleSection());
                    break;

                case 3:
                    roomService.displayBuildings(
                            dormitory.getFemaleSection());
                    break;

                case 4:
                    displayRoomsInBuilding();
                    break;

                case 5:
                    searchBuilding();
                    break;

                case 6:
                    searchRoom();
                    break;

                case 0:
                    System.out.println(
                            "Exiting Room Management System...");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    // Display rooms in a selected building
    private void displayRoomsInBuilding() {

        System.out.println("\n===== DISPLAY BUILDING ROOMS =====");

        SectionType sectionType = readSectionType();

        System.out.print("Enter Building Name: ");
        String buildingName = scanner.nextLine();

        roomService.displayRoomsInBuilding(
                dormitory,
                sectionType,
                buildingName);
    }

    // Search building
    private void searchBuilding() {

        System.out.println("\n===== SEARCH BUILDING =====");

        SectionType sectionType = readSectionType();

        System.out.print("Enter Building Name: ");
        String buildingName = scanner.nextLine();

        Block building = roomService.findBuilding(
                dormitory,
                sectionType,
                buildingName);

        if (building != null) {

            System.out.println("\nBuilding found!");
            building.displayBlockInformation();

        } else {

            System.out.println("Building not found.");
        }
    }

    // Search room
    private void searchRoom() {

        System.out.println("\n===== SEARCH ROOM =====");

        SectionType sectionType = readSectionType();

        System.out.print("Enter Room Number: ");
        String roomNumber = scanner.nextLine();

        Room room = roomService.searchRoom(
                dormitory,
                sectionType,
                roomNumber);

        if (room != null) {

            System.out.println("\nRoom found!");
            room.displayRoomInformation();

        } else {

            System.out.println("Room not found.");
        }
    }

    // Read section type
    private SectionType readSectionType() {

        System.out.println("\nSelect Section:");
        System.out.println("1. Male");
        System.out.println("2. Female");

        while (true) {

            System.out.print("Enter choice: ");

            int choice = readInteger();

            if (choice == 1) {
                return SectionType.MALE;
            }

            if (choice == 2) {
                return SectionType.FEMALE;
            }

            System.out.println(
                    "Invalid selection. Choose 1 or 2.");
        }
    }

    // Read integer safely
    private int readInteger() {

        while (true) {

            String input = scanner.nextLine();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.print(
                        "Invalid number. Enter a valid choice: ");
            }
        }
    }
}
