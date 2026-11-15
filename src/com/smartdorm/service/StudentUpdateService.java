
package com.smartdorm.service;

import com.smartdorm.model.Student;
import com.smartdorm.enums.AcademicYear;

public class StudentUpdateService {

    private StudentRegistrationService registrationService;

    // Constructor
    public StudentUpdateService(
            StudentRegistrationService registrationService) {

        if (registrationService == null) {
            throw new IllegalArgumentException(
                    "Registration service cannot be null."
            );
        }

        this.registrationService = registrationService;
    }

    // Update student information
    public boolean updateStudent(
            String studentId,
            String fullName,
            String email,
            String phoneNumber,
            String department,
            AcademicYear academicYear) {

        // Validate input
        if (studentId == null || studentId.trim().isEmpty()
                || fullName == null || fullName.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || phoneNumber == null || phoneNumber.trim().isEmpty()
                || department == null || department.trim().isEmpty()
                || academicYear == null) {

            System.out.println("Invalid student information.");
            return false;
        }

        // Find registered student
        Student student =
                registrationService.findStudentById(studentId);

        if (student == null) {
            System.out.println("Student is not registered.");
            return false;
        }

        // Update student details
        student.setFullName(fullName.trim());
        student.setEmail(email.trim());
        student.setPhoneNumber(phoneNumber.trim());
        student.setDepartment(department.trim());
        student.setAcademicYear(academicYear);

        System.out.println("Student information updated successfully!");

        return true;
    }
}
