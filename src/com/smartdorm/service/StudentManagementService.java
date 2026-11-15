
package com.smartdorm.service;

import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Student;

public class StudentManagementService {

    private StudentRegistrationService registrationService;
    private RoomAllocationService allocationService;

    // Constructor
    public StudentManagementService(
            StudentRegistrationService registrationService,
            RoomAllocationService allocationService) {

        if (registrationService == null || allocationService == null) {
            throw new IllegalArgumentException(
                    "Services cannot be null."
            );
        }

        this.registrationService = registrationService;
        this.allocationService = allocationService;
    }

    // Remove student from registration and room allocation
    public boolean removeStudent(
            Dormitory dormitory,
            String studentId) {

        if (dormitory == null
                || studentId == null
                || studentId.trim().isEmpty()) {

            System.out.println("Invalid dormitory or student ID.");
            return false;
        }

        // First, confirm that the student is registered.
        Student student =
                registrationService.findStudentById(studentId);

        if (student == null) {
            System.out.println("Student is not registered.");
            return false;
        }

        // Remove the room allocation if the student has one.
        boolean allocated =
                allocationService.removeStudentFromDormitory(
                        dormitory, studentId);

        // Remove the registration record.
        boolean registered =
                registrationService.removeRegisteredStudent(
                        studentId);

        System.out.println("\n===== STUDENT REMOVAL SUMMARY =====");
        System.out.println("Student: " + student.getFullName());
        System.out.println("Room Allocation Removed: " + allocated);
        System.out.println("Registration Removed: " + registered);

        return registered;
    }


}
