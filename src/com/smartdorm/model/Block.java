
package com.smartdorm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartdorm.enums.BlockStatus;
import com.smartdorm.enums.SectionType;
import com.smartdorm.enums.AcademicYear;

public class Block {

    private String blockName;

    // Previously reported student population
    private int reportedAssignedStudents;

    private BlockStatus status;
    private SectionType sectionType;

    private Map<String, Set<AcademicYear>> eligibilityRules;

    private List<Room> rooms;

    // Constructor
    public Block(
            String blockName,
            int reportedAssignedStudents,
            BlockStatus status) {

        if (blockName == null || blockName.isBlank()
                || reportedAssignedStudents < 0
                || status == null) {

            throw new IllegalArgumentException(
                    "Invalid block information.");
        }

        this.blockName = blockName;
        this.reportedAssignedStudents = reportedAssignedStudents;
        this.status = status;

        this.eligibilityRules = new HashMap<>();
        this.rooms = new ArrayList<>();
    }

    // Getters
    public String getBlockName() {
        return blockName;
    }

    // Total installed physical bed capacity
    public int getCapacity() {
        return getTotalRoomCapacity();
    }

    // Previously reported assigned student count
    public int getReportedAssignedStudents() {
        return reportedAssignedStudents;
    }

    public BlockStatus getStatus() {
        return status;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public void setStatus(BlockStatus status) {
        if (status == null) {
            throw new IllegalArgumentException(
                    "Block status cannot be null.");
        }

        this.status = status;
    }

    // Configure department and academic-year eligibility
    public void configureEligibility(
            SectionType sectionType,
            Map<String, Set<AcademicYear>> rules) {

        if (sectionType == null || rules == null) {
            throw new IllegalArgumentException(
                    "Section type and eligibility rules are required.");
        }

        this.sectionType = sectionType;
        this.eligibilityRules.clear();

        rules.forEach((department, years) -> {

            if (department != null && years != null
                    && !years.isEmpty()) {

                this.eligibilityRules.put(
                        department.trim(),
                        Set.copyOf(years)
                );
            }
        });
    }

 
// Check student eligibility
public boolean isEligible(Student student) {

    if (student == null
            || student.getGender() == null
            || student.getDepartment() == null
            || student.getAcademicYear() == null
            || sectionType == null) {
        return false;
    }

    // Check gender separation.
    if (!student.getGender().name()
            .equals(sectionType.name())) {
        return false;
    }

    String department = student.getDepartment().trim();

    // First, check the specific department rule.
    Set<AcademicYear> allowedYears =
            eligibilityRules.get(department);

    if (allowedYears != null
            && allowedYears.contains(student.getAcademicYear())) {
        return true;
    }

    // If the specific rule does not allow the year,
    // check the general all-departments rule.
    Set<AcademicYear> allDepartmentYears =
            eligibilityRules.get("*");

    return allDepartmentYears != null
            && allDepartmentYears.contains(student.getAcademicYear());
}

    // Add room without confusing assigned students with capacity
    public boolean addRoom(Room room) {

        if (room == null) {
            return false;
        }

        for (Room existingRoom : rooms) {

            if (existingRoom.getRoomNumber().equalsIgnoreCase(
                    room.getRoomNumber())) {

                return false;
            }
        }

        rooms.add(room);
        return true;
    }

    // Count rooms
    public int getNumberOfRooms() {
        return rooms.size();
    }

    // Calculate installed room capacity
    public int getTotalRoomCapacity() {

        int total = 0;

        for (Room room : rooms) {
            total += room.getCapacity();
        }

        return total;
    }

    // Calculate currently occupied beds
    public int getOccupiedBeds() {

        int occupied = 0;

        for (Room room : rooms) {
            occupied += room.getStudents().size();
        }

        return occupied;
    }

    // Calculate available beds
    public int getAvailableBeds() {

        return Math.max(
                0,
                getTotalRoomCapacity() - getOccupiedBeds()
        );
    }

    // Check allocation availability
    public boolean canAllocateStudents() {

        return status == BlockStatus.ACTIVE
                && getAvailableBeds() > 0;
    }

    // Display block information
    public void displayBlockInformation() {

        System.out.println("\n===== BLOCK INFORMATION =====");

        System.out.println("Block Name: " + blockName);
        System.out.println("Status: " + status);

        System.out.println(
                "Previously Reported Assigned Students: "
                + reportedAssignedStudents
        );

        System.out.println("Number of Rooms: " + getNumberOfRooms());

        System.out.println(
                "Physical Bed Capacity: " + getCapacity()
        );

        System.out.println("Occupied Beds: " + getOccupiedBeds());

        System.out.println("Available Beds: " + getAvailableBeds());
    }
}
