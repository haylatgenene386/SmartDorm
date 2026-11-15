
package com.smartdorm.model;

public class Staff extends Person {

    private String staffId;
    private String position;
    private String assignedSection;

    // Constructor
    public Staff(
            String id,
            String fullName,
            String email,
            String phoneNumber,
            String staffId,
            String position,
            String assignedSection) {

        super(id, fullName, email, phoneNumber);

        this.staffId = staffId;
        this.position = position;
        this.assignedSection = assignedSection;
    }

    // Getters
    public String getStaffId() {
        return staffId;
    }

    public String getPosition() {
        return position;
    }

    public String getAssignedSection() {
        return assignedSection;
    }

    // Display staff information
    @Override
    public void displayInformation() {

        System.out.println("Staff Information");
        System.out.println("-----------------");
        System.out.println("Name: " + getFullName());
        System.out.println("Staff ID: " + staffId);
        System.out.println("Position: " + position);
        System.out.println("Assigned Section: " + assignedSection);
        System.out.println("Email: " + getEmail());
    }
}
