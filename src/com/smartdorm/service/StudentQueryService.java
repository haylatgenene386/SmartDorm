
package com.smartdorm.service;

import java.util.List;

import com.smartdorm.model.Student;

public class StudentQueryService {

    private StudentRegistrationService registrationService;

    // Constructor
    public StudentQueryService(
            StudentRegistrationService registrationService) {

        if (registrationService == null) {
            throw new IllegalArgumentException(
                    "Registration service cannot be null."
            );
        }

        this.registrationService = registrationService;
    }

    // Display all registered students
    public void displayAllStudents() {

        List<Student> students =
                registrationService.getRegisteredStudents();

        if (students.isEmpty()) {
            System.out.println("No students are registered.");
            return;
        }

        System.out.println("\n===== REGISTERED STUDENTS =====");

        for (Student student : students) {
            System.out.println("----------------------------");
            student.displayInformation();
        }

        System.out.println("----------------------------");
        System.out.println("Total Students: " + students.size());
    }

    // Search student by ID
    public Student searchByStudentId(String studentId) {

        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("Invalid student ID.");
            return null;
        }

        Student student =
                registrationService.findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return null;
        }

        System.out.println("\n===== STUDENT FOUND =====");
        student.displayInformation();

        return student;
    }

    // Search students by name
    public void searchByName(String name) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid student name.");
            return;
        }

        List<Student> students =
                registrationService.getRegisteredStudents();

        int matches = 0;

        System.out.println("\n===== STUDENT SEARCH RESULTS =====");

        for (Student student : students) {

            if (student.getFullName() != null
                    && student.getFullName()
                    .toLowerCase()
                    .contains(name.trim().toLowerCase())) {

                student.displayInformation();
                System.out.println("----------------------------");
                matches++;
            }
        }

        if (matches == 0) {
            System.out.println("No matching students found.");
        } else {
            System.out.println("Matching Students: " + matches);
        }
    }

    // Display total registered students
    public void displayTotalStudents() {

        System.out.println(
                "Total Registered Students: "
                + registrationService.getTotalRegisteredStudents()
        );
    }
}
