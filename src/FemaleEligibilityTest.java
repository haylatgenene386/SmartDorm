
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class FemaleEligibilityTest {

    public static void main(String[] args) {

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        test(dormitory, "F001", "Law",
                AcademicYear.FIFTH_YEAR, true, "Twins A");

        test(dormitory, "F002", "SWE",
                AcademicYear.FIFTH_YEAR, true, "Twins A");

        test(dormitory, "F003", "IT",
                AcademicYear.FRESHMAN, true, "SAT 2A");

        test(dormitory, "F004", "IT",
                AcademicYear.SECOND_YEAR, true, "3A");

        test(dormitory, "F005", "CS",
                AcademicYear.FOURTH_YEAR, true, "3A");

        test(dormitory, "F006", "SWE",
                AcademicYear.FOURTH_YEAR, true, "3A");

        test(dormitory, "F007", "IT",
                AcademicYear.FOURTH_YEAR, false, null);

    }

    private static void test(
            Dormitory dormitory,
            String id,
            String department,
            AcademicYear year,
            boolean expected,
            String expectedBlock) {

        Student student = new Student(
                id, "Test Female", "test@gmail.com",
                "0912345678", id, department,
                year, Gender.FEMALE);

        RoomAllocationService allocation =
                new RoomAllocationService();

        boolean result =
                allocation.allocateStudent(dormitory, student);

        boolean passed = result == expected;

        if (expected && result) {
            passed = allocationResultMatches(
                    dormitory, id, expectedBlock);
        }

        System.out.println(
                (passed ? "PASS" : "FAIL")
                + " | " + department
                + " | " + year
                + " | Expected: " + expectedBlock
        );
    }

    private static boolean allocationResultMatches(
            Dormitory dormitory,
            String studentId,
            String expectedBlock) {

        for (Block block :
                dormitory.getFemaleSection().getBlocks()) {

            for (Room room : block.getRooms()) {

                for (Student student : room.getStudents()) {

                    if (student.getStudentId().equals(studentId)) {
                        return block.getBlockName()
                                .equalsIgnoreCase(expectedBlock);
                    }
                }
            }
        }

        return false;
    }
}
