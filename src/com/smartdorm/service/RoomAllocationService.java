
package com.smartdorm.service;

import com.smartdorm.model.Dormitory;
import com.smartdorm.model.DormitorySection;
import com.smartdorm.model.Block;
import com.smartdorm.model.Room;
import com.smartdorm.model.Student;

import com.smartdorm.enums.SectionType;
import java.util.List;

public class RoomAllocationService {

    // Main student allocation method
    public boolean allocateStudent(
            Dormitory dormitory,
            Student student) {

        if (dormitory == null || student == null) {
            System.out.println("Invalid student or dormitory.");
            return false;
        }

        if (student.getGender() == null) {
            System.out.println("Student gender is not specified.");
            return false;
        }

        SectionType sectionType =
                SectionType.valueOf(student.getGender().name());

        DormitorySection section =
                dormitory.getSection(sectionType);

        // Prevent duplicate allocation
        if (isAlreadyAllocated(dormitory, student.getStudentId())) {
            System.out.println("Student is already allocated.");
            return false;
        }

        // PRIORITY ALLOCATION
        if (student.isSpecialAccommodationNeeded()) {

            String priorityBlockName;

            if (sectionType == SectionType.MALE) {
                priorityBlockName = "Building I";
            } else {
                priorityBlockName = "Twins A";
            }

            return allocateSpecialAccommodationStudent(
                    section,
                    student,
                    priorityBlockName
            );
        }

        // NORMAL ALLOCATION
        return allocateRegularStudent(section, student);
    }

    // Allocate multiple students with special students first.
    public int allocateStudentsInPriorityOrder(
            Dormitory dormitory,
            List<Student> students) {

        if (dormitory == null || students == null) {
            System.out.println("Invalid dormitory or student list.");
            return 0;
        }

        int successfulAllocations = 0;

        // FIRST PASS: Allocate special-accommodation students.
        for (Student student : students) {

            if (student != null
                    && student.isSpecialAccommodationNeeded()) {

                if (allocateStudent(dormitory, student)) {
                    successfulAllocations++;
                }
            }
        }

        // SECOND PASS: Allocate regular students.
        for (Student student : students) {

            if (student != null
                    && !student.isSpecialAccommodationNeeded()) {

                if (allocateStudent(dormitory, student)) {
                    successfulAllocations++;
                }
            }
        }

        System.out.println("\n===== BULK ALLOCATION SUMMARY =====");
        System.out.println("Total Registered: " + students.size());
        System.out.println("Successfully Allocated: "
                + successfulAllocations);
        System.out.println("Failed Allocations: "
                + (students.size() - successfulAllocations));

        return successfulAllocations;
    }


    // Allocate students needing special accommodation
    private boolean allocateSpecialAccommodationStudent(
            DormitorySection section,
            Student student,
            String priorityBlockName) {

        for (Block block : section.getBlocks()) {

            // Find the designated priority block
            if (!block.getBlockName().equalsIgnoreCase(
                    priorityBlockName)) {
                continue;
            }

            // Check whether the block is active and has capacity
            if (!block.canAllocateStudents()) {
                break;
            }

            // Special accommodation students must get first-floor rooms
            for (Room room : block.getRooms()) {

                if (room.isFirstFloor() && !room.isFull()) {

                    if (room.addStudent(student)) {

                        displayAllocation(
                                student,
                                block,
                                room,
                                "SPECIAL ACCOMMODATION PRIORITY"
                        );

                        return true;
                    }
                }
            }

            break;
        }

        System.out.println(
                "No available first-floor priority room found for "
                + student.getFullName()
        );

        return false;
    }


    // Allocate students without special accommodation needs

private boolean allocateRegularStudent(
        DormitorySection section,
        Student student) {

    String priorityBlockName =
            student.getGender() == com.smartdorm.enums.Gender.MALE
            ? "Building I"
            : "Twins A";

    // First pass: Find the designated priority building.
    for (Block block : section.getBlocks()) {

        if (!block.getBlockName().equalsIgnoreCase(priorityBlockName)) {
            continue;
        }

        if (!block.isEligible(student) || !block.canAllocateStudents()) {
            break;
        }

        // PRIORITY: Share first-floor rooms occupied by
        // special-accommodation students.
        for (Room room : block.getRooms()) {

            if (!room.isFirstFloor() || room.isFull()) {
                continue;
            }

            boolean containsSpecialStudent = false;

            for (Student existing : room.getStudents()) {
                if (existing.isSpecialAccommodationNeeded()) {
                    containsSpecialStudent = true;
                    break;
                }
            }

            if (containsSpecialStudent && room.addStudent(student)) {

                displayAllocation(
                        student,
                        block,
                        room,
                        "NORMAL ALLOCATION - SPECIAL ROOM SHARING"
                );

                return true;
            }
        }

        // SECOND PRIORITY: Other available rooms
        // in the designated priority building.
        for (Room room : block.getRooms()) {

            if (!room.isFull() && room.addStudent(student)) {

                displayAllocation(
                        student,
                        block,
                        room,
                        "NORMAL ALLOCATION - PRIORITY BUILDING"
                );

                return true;
            }
        }

        break;
    }

    // Second pass: Other eligible buildings.
    for (Block block : section.getBlocks()) {

        if (block.getBlockName().equalsIgnoreCase(priorityBlockName)) {
            continue;
        }

        if (!block.isEligible(student) || !block.canAllocateStudents()) {
            continue;
        }

        for (Room room : block.getRooms()) {

            if (!room.isFull() && room.addStudent(student)) {

                displayAllocation(
                        student,
                        block,
                        room,
                        "NORMAL ALLOCATION"
                );

                return true;
            }
        }
    }

    System.out.println(
            "No eligible room is currently available for "
            + student.getFullName()
    );

    return false;
}


// Remove a student from anywhere in the dormitory
public boolean removeStudentFromDormitory(
        Dormitory dormitory,
        String studentId) {

    if (dormitory == null
            || studentId == null
            || studentId.trim().isEmpty()) {

        System.out.println("Invalid dormitory or student ID.");
        return false;
    }

    // Search both male and female sections.
    DormitorySection[] sections = {
        dormitory.getMaleSection(),
        dormitory.getFemaleSection()
    };

    for (DormitorySection section : sections) {

        for (Block block : section.getBlocks()) {

            for (Room room : block.getRooms()) {

                if (room.removeStudent(studentId)) {

                    System.out.println(
                            "\nStudent removed successfully!"
                    );

                    System.out.println(
                            "Student ID: " + studentId
                    );

                    System.out.println(
                            "Block: " + block.getBlockName()
                    );

                    System.out.println(
                            "Room: " + room.getRoomNumber()
                    );

                    System.out.println(
                            "Available Beds: "
                            + room.getAvailableBeds()
                    );

                    return true;
                }
            }
        }
    }

    System.out.println("Student is not currently allocated.");
    return false;
}


    // Display successful allocation information
    private void displayAllocation(
            Student student,
            Block block,
            Room room,
            String allocationType) {

        System.out.println("\nStudent allocated successfully!");
        System.out.println("Allocation Type: " + allocationType);
        System.out.println("Student: " + student.getFullName());
        System.out.println("Block: " + block.getBlockName());
        System.out.println("Room: " + room.getRoomNumber());
        System.out.println("Floor: " + room.getFloorNumber());
        System.out.println(
                "Available Beds: " + room.getAvailableBeds()
        );
    }


    // Check whether student already has a room
    private boolean isAlreadyAllocated(
            Dormitory dormitory,
            String studentId) {

        for (Block block :
                dormitory.getMaleSection().getBlocks()) {

            for (Room room : block.getRooms()) {

                for (Student existing : room.getStudents()) {

                    if (existing.getStudentId().equals(studentId)) {
                        return true;
                    }
                }
            }
        }

        for (Block block :
                dormitory.getFemaleSection().getBlocks()) {

            for (Room room : block.getRooms()) {

                for (Student existing : room.getStudents()) {

                    if (existing.getStudentId().equals(studentId)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
