
package com.smartdorm.service;

import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Student;

public class StudentManagementController {

    private Dormitory dormitory;

    private StudentRegistrationService registrationService;
    private RoomAllocationService allocationService;
    private StudentManagementService managementService;
    private StudentUpdateService updateService;
    private StudentQueryService queryService;

    // Constructor
    public StudentManagementController(Dormitory dormitory) {

        if (dormitory == null) {
            throw new IllegalArgumentException(
                    "Dormitory cannot be null."
            );
        }

        this.dormitory = dormitory;

        registrationService =
                new StudentRegistrationService();

        allocationService =
                new RoomAllocationService();

        managementService =
                new StudentManagementService(
                        registrationService,
                        allocationService
                );

        updateService =
                new StudentUpdateService(registrationService);

        queryService =
                new StudentQueryService(registrationService);
    }

    // Register student
    public boolean registerStudent(Student student) {
        return registrationService.registerStudent(student);
    }

    // Allocate a registered student
    public boolean allocateStudent(String studentId) {

        Student student =
                registrationService.findStudentById(studentId);

        if (student == null) {
            System.out.println("Student is not registered.");
            return false;
        }

        return allocationService.allocateStudent(
                dormitory, student
        );
    }

    // Update student information
    public boolean updateStudent(
            String studentId,
            String fullName,
            String email,
            String phoneNumber,
            String department,
            com.smartdorm.enums.AcademicYear academicYear) {

        return updateService.updateStudent(
                studentId,
                fullName,
                email,
                phoneNumber,
                department,
                academicYear
        );
    }

    // Search student by ID
    public Student searchStudent(String studentId) {
        return queryService.searchByStudentId(studentId);
    }

    // Search students by name
    public void searchStudentsByName(String name) {
        queryService.searchByName(name);
    }

    // Display all students
    public void displayAllStudents() {
        queryService.displayAllStudents();
    }

    // Remove student
    public boolean removeStudent(String studentId) {
        return managementService.removeStudent(
                dormitory, studentId
        );
    }

    // Display total registered students
    public void displayTotalStudents() {
        queryService.displayTotalStudents();
    }

    // Get dormitory information
    public Dormitory getDormitory() {
        return dormitory;
    }
}
