
package com.smartdorm.service;

import java.util.ArrayList;
import java.util.List;

import com.smartdorm.model.Student;

public class StudentRegistrationService {

    private List<Student> registeredStudents;

    // Constructor
    public StudentRegistrationService() {
        registeredStudents = new ArrayList<>();
    }

    // Register a new student
    public boolean registerStudent(Student student) {

        if (student == null) {
            System.out.println("Invalid student information.");
            return false;
        }

        if (student.getStudentId() == null
                || student.getStudentId().trim().isEmpty()) {
            System.out.println("Student ID is required.");
            return false;
        }

        if (student.getFullName() == null
                || student.getFullName().trim().isEmpty()) {
            System.out.println("Student name is required.");
            return false;
        }

        // Check duplicate student ID
        for (Student existing : registeredStudents) {

            if (existing.getStudentId().equalsIgnoreCase(
                    student.getStudentId().trim())) {

                System.out.println("Student ID already registered.");
                return false;
            }
        }

        registeredStudents.add(student);

        System.out.println("Student registered successfully!");

        return true;
    }

    // Return all registered students
    public List<Student> getRegisteredStudents() {
        return new ArrayList<>(registeredStudents);
    }

    // Find student using student ID
    public Student findStudentById(String studentId) {

        if (studentId == null || studentId.trim().isEmpty()) {
            return null;
        }

        for (Student student : registeredStudents) {

            if (student.getStudentId().equalsIgnoreCase(
                    studentId.trim())) {
                return student;
            }
        }

        return null;
    }

    // Get total registered students
    public int getTotalRegisteredStudents() {
        return registeredStudents.size();
    }


    // Remove a student from the registration list
    public boolean removeRegisteredStudent(String studentId) {

        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("Invalid student ID.");
            return false;
        }

        boolean removed = registeredStudents.removeIf(student ->
                student.getStudentId() != null
                && student.getStudentId().equalsIgnoreCase(
                        studentId.trim())
        );

        if (removed) {
            System.out.println(
                    "Student registration removed successfully!"
            );
        } else {
            System.out.println(
                    "Student registration not found."
            );
        }

        return removed;
    }

}
