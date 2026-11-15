
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class DuplicateStudentTest {

    public static void main(String[] args) {

        System.out.println("===== DUPLICATE STUDENT TEST =====");

        // TEST 1: Duplicate student in the same room

        Room room = new Room("DUP-001", 1);

        Student student1 = new Student(
            "P001", "Test Student",
            "test@gmail.com", "0911111111",
            "HU001", "IT",
            AcademicYear.THIRD_YEAR,
            Gender.MALE, false
        );

        Student duplicateStudent = new Student(
            "P002", "Test Student Duplicate",
            "duplicate@gmail.com", "0922222222",
            "HU001", "IT",
            AcademicYear.THIRD_YEAR,
            Gender.MALE, false
        );

        boolean firstAdd = room.addStudent(student1);
        boolean duplicateAdd = room.addStudent(duplicateStudent);

        System.out.println("\nTEST 1: Same Room");

        System.out.println("First registration: "
                + (firstAdd ? "ACCEPTED" : "REJECTED"));

        System.out.println("Duplicate registration: "
                + (duplicateAdd ? "ACCEPTED" : "REJECTED"));

        System.out.println("Room Occupancy: "
                + room.getStudents().size());


        // TEST 2: Duplicate allocation across the dormitory

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        RoomAllocationService allocationService =
                new RoomAllocationService();

        Student student = new Student(
            "P003", "Allocation Test Student",
            "allocation@gmail.com", "0933333333",
            "HU003", "Sociology",
            AcademicYear.FOURTH_YEAR,
            Gender.MALE, false
        );

        boolean firstAllocation =
                allocationService.allocateStudent(dormitory, student);

        boolean secondAllocation =
                allocationService.allocateStudent(dormitory, student);

        System.out.println("\nTEST 2: Dormitory Allocation");

        System.out.println("First allocation: "
                + (firstAllocation ? "SUCCESS" : "FAILED"));

        System.out.println("Second allocation: "
                + (secondAllocation ? "SUCCESS" : "REJECTED"));

        // Final verification

        if (firstAdd && !duplicateAdd
                && room.getStudents().size() == 1
                && firstAllocation && !secondAllocation) {

            System.out.println(
                "\nPASS: Duplicate student prevention works correctly!"
            );

        } else {

            System.out.println(
                "\nFAIL: Duplicate student prevention needs correction."
            );
        }
    }
}
