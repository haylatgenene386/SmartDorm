
package com.smartdorm.model;
import com.smartdorm.enums.Gender;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private static final int ROOM_CAPACITY = 6;

    private String roomNumber;
    private int floorNumber;
    private List<Student> students;

    public Room(String roomNumber, int floorNumber) {

if (floorNumber < 0) {
    throw new IllegalArgumentException(
        "Floor number cannot be negative."
    );
}

        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.students = new ArrayList<>();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isFirstFloor() {
        return floorNumber == 1;
    }

    public int getCapacity() {
        return ROOM_CAPACITY;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public boolean isFull() {
        return students.size() >= ROOM_CAPACITY;
    }

    public int getAvailableBeds() {
        return ROOM_CAPACITY - students.size();
    }

    
public boolean addStudent(Student student) {

    if (student == null || isFull()) {
        return false;
    }

    // Gender must be specified.
    if (student.getGender() == null) {
        return false;
    }

    // Prevent different genders from sharing one room.
    if (!students.isEmpty()) {

        Gender existingGender = students.get(0).getGender();

        if (existingGender == null
                || existingGender != student.getGender()) {
            return false;
        }
    }

    // Prevent duplicate student registration.
    for (Student existing : students) {

        if (existing.getStudentId().equals(student.getStudentId())) {
            return false;
        }
    }

    students.add(student);
    return true;
}


public boolean removeStudent(String studentId) {

    if (studentId == null || studentId.trim().isEmpty()) {
        return false;
    }

    return students.removeIf(student ->
        student.getStudentId() != null
        && student.getStudentId().equalsIgnoreCase(
                studentId.trim())
    );
}

    public void displayRoomInformation() {

        System.out.println("Room Number: " + roomNumber);
        System.out.println("Floor Number: " + floorNumber);
        System.out.println("Capacity: " + ROOM_CAPACITY);
        System.out.println("Occupied Beds: " + students.size());
        System.out.println("Available Beds: " + getAvailableBeds());
        System.out.println("Room Full: " + isFull());
    }
}
