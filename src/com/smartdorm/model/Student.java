
package com.smartdorm.model;

import com.smartdorm.enums.Gender;
import com.smartdorm.enums.AcademicYear;

public class Student extends Person {

    private String studentId;
    private String department;
    private AcademicYear academicYear;
    private Gender gender;

    // Special accommodation information
    private boolean specialAccommodationNeeded;

    // Original constructor - keeps existing code compatible
    public Student(
            String id,
            String fullName,
            String email,
            String phoneNumber,
            String studentId,
            String department,
            AcademicYear academicYear,
            Gender gender) {

        this(
            id,
            fullName,
            email,
            phoneNumber,
            studentId,
            department,
            academicYear,
            gender,
            false
        );
    }

    // Constructor with special accommodation information
    public Student(
            String id,
            String fullName,
            String email,
            String phoneNumber,
            String studentId,
            String department,
            AcademicYear academicYear,
            Gender gender,
            boolean specialAccommodationNeeded) {

        super(id, fullName, email, phoneNumber);

        this.studentId = studentId;
        this.department = department;
        this.academicYear = academicYear;
        this.gender = gender;
        this.specialAccommodationNeeded = specialAccommodationNeeded;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDepartment() {
        return department;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public Gender getGender() {
        return gender;
    }
// Update student's department
public void setDepartment(String department) {
    this.department = department;
}

// Update student's academic year
public void setAcademicYear(AcademicYear academicYear) {
    this.academicYear = academicYear;
}
    public boolean isSpecialAccommodationNeeded() {
        return specialAccommodationNeeded;
    }

    public void setSpecialAccommodationNeeded(boolean needed) {
        this.specialAccommodationNeeded = needed;
    }

    @Override
    public void displayInformation() {

        System.out.println("Student Information");
        System.out.println("-------------------");
        System.out.println("Name: " + getFullName());
        System.out.println("Student ID: " + studentId);
        System.out.println("Department: " + department);
        System.out.println("Academic Year: " + academicYear);
        System.out.println("Gender: " + gender);
        System.out.println("Email: " + getEmail());

        // Do not display confidential accommodation information here.
    }
}
