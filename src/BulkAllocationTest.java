
import java.util.ArrayList;
import java.util.List;

import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class BulkAllocationTest {

    public static void main(String[] args) {

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        RoomAllocationService allocation =
                new RoomAllocationService();

        List<Student> students = new ArrayList<>();

        // Regular students registered first.
        students.add(new Student(
                "B001", "Regular Male",
                "b1@gmail.com", "0911111111",
                "B001", "Sociology",
                AcademicYear.FOURTH_YEAR,
                Gender.MALE, false));

        students.add(new Student(
                "B002", "Regular Female",
                "b2@gmail.com", "0922222222",
                "B002", "Law",
                AcademicYear.FIFTH_YEAR,
                Gender.FEMALE, false));

        // Special students registered later.
        students.add(new Student(
                "B003", "Special Male",
                "b3@gmail.com", "0933333333",
                "B003", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.MALE, true));

        students.add(new Student(
                "B004", "Special Female",
                "b4@gmail.com", "0944444444",
                "B004", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.FEMALE, true));

        // Execute bulk allocation.
        int successful =
                allocation.allocateStudentsInPriorityOrder(
                        dormitory, students);

        System.out.println("\n===== BULK TEST RESULTS =====");

        System.out.println("Expected allocations: 4");
        System.out.println("Actual allocations: " + successful);

        checkStudent(dormitory, "B003", "Building I", true);
        checkStudent(dormitory, "B004", "Twins A", true);
        checkStudent(dormitory, "B001", "Building I", false);
        checkStudent(dormitory, "B002", "Twins A", false);
    }

    private static void checkStudent(
            Dormitory dormitory,
            String studentId,
            String expectedBlock,
            boolean special) {

        for (Block block :
                dormitory.getMaleSection().getBlocks()) {

            for (Room room : block.getRooms()) {

                for (Student student : room.getStudents()) {

                    if (student.getStudentId().equals(studentId)) {
                        boolean correct =
                                block.getBlockName()
                                        .equalsIgnoreCase(expectedBlock)
                                && student.isSpecialAccommodationNeeded()
                                        == special;

                        System.out.println(
                                (correct ? "PASS" : "FAIL")
                                + " | " + studentId
                                + " | " + block.getBlockName()
                        );

                        return;
                    }
                }
            }
        }

        for (Block block :
                dormitory.getFemaleSection().getBlocks()) {

            for (Room room : block.getRooms()) {

                for (Student student : room.getStudents()) {

                    if (student.getStudentId().equals(studentId)) {
                        boolean correct =
                                block.getBlockName()
                                        .equalsIgnoreCase(expectedBlock)
                                && student.isSpecialAccommodationNeeded()
                                        == special;

                        System.out.println(
                                (correct ? "PASS" : "FAIL")
                                + " | " + studentId
                                + " | " + block.getBlockName()
                        );

                        return;
                    }
                }
            }
        }

        System.out.println("FAIL | " + studentId + " | Not allocated");
    }
}
