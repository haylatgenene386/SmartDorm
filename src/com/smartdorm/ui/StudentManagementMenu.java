
package com.smartdorm.ui;

import java.util.Scanner;

import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Student;
import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.service.StudentManagementController;

import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;


public class StudentManagementMenu {

    private static Scanner scanner = new Scanner(System.in);

    private StudentManagementController controller;

   public StudentManagementMenu(Dormitory dormitory) {

    controller = new StudentManagementController(dormitory);
}
    // Display menu
    public void displayMenu() {

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("==================================");

            System.out.println("1. Register Student");
            System.out.println("2. Allocate Student to Room");
            System.out.println("3. Update Student Information");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Search Students by Name");
            System.out.println("6. Display All Students");
            System.out.println("7. Display Total Students");
            System.out.println("8. Remove Student");
            System.out.println("0. Exit");

            System.out.println("----------------------------------");
            System.out.print("Enter your choice: ");

            choice = readInteger();

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    allocateStudent();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    searchStudent();
                    break;

                case 5:
                    searchStudentsByName();
                    break;

                case 6:
                    controller.displayAllStudents();
                    break;

                case 7:
                    controller.displayTotalStudents();
                    break;

                case 8:
                    removeStudent();
                    break;

                case 0:
                    System.out.println(
                            "Exiting Student Management System...");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    // Register student
    private void registerStudent() {

        System.out.println("\n===== STUDENT REGISTRATION =====");

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("University ID: ");
        String universityId = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        AcademicYear academicYear =
                readEnum(AcademicYear.class);

        Gender gender =
                readEnum(Gender.class);

        Student student = new Student(
                studentId,
                fullName,
                email,
                phoneNumber,
                universityId,
                department,
                academicYear,
                gender
        );

        controller.registerStudent(student);
    }

    // Allocate student
    private void allocateStudent() {

        System.out.println("\n===== ROOM ALLOCATION =====");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        controller.allocateStudent(studentId);
    }

    // Update student
    private void updateStudent() {

        System.out.println("\n===== UPDATE STUDENT =====");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("New Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("New Email: ");
        String email = scanner.nextLine();

        System.out.print("New Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("New Department: ");
        String department = scanner.nextLine();

        AcademicYear academicYear =
                readEnum(AcademicYear.class);

        controller.updateStudent(
                studentId,
                fullName,
                email,
                phoneNumber,
                department,
                academicYear
        );
    }

    // Search student by ID
    private void searchStudent() {

        System.out.println("\n===== SEARCH STUDENT =====");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        controller.searchStudent(studentId);
    }

    // Search students by name
    private void searchStudentsByName() {

        System.out.println("\n===== SEARCH BY NAME =====");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        controller.searchStudentsByName(name);
    }

    // Remove student
    private void removeStudent() {

        System.out.println("\n===== REMOVE STUDENT =====");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        controller.removeStudent(studentId);
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

    // Read enum values dynamically
    private <T extends Enum<T>> T readEnum(
            Class<T> enumClass) {

        T[] values = enumClass.getEnumConstants();

        System.out.println("\nAvailable "
                + enumClass.getSimpleName() + " values:");

        for (int i = 0; i < values.length; i++) {

            System.out.println(
                    (i + 1) + ". " + values[i]);
        }

        while (true) {

            System.out.print("Select number: ");

            int choice = readInteger();

            if (choice >= 1 && choice <= values.length) {

                return values[choice - 1];
            }

            System.out.println(
                    "Invalid selection. Please try again.");
        }
    }

    // Main method
   public static void main(String[] args) {

    DormitoryConfigurationService config =
            new DormitoryConfigurationService();

    Dormitory dormitory = config.configureDormitory();

    StudentManagementMenu menu =
            new StudentManagementMenu(dormitory);

    menu.displayMenu();
}
}
