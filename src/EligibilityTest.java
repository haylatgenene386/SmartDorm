
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class EligibilityTest {

    public static void main(String[] args) {

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        RoomAllocationService allocationService =
                new RoomAllocationService();

        System.out.println("\n===== ELIGIBILITY TEST =====");

        // TEST 1: Sociology - Fourth Year
        Student student1 = createStudent(
                "HU101", "Sociology",
                AcademicYear.FOURTH_YEAR
        );

        boolean result1 =
                allocationService.allocateStudent(dormitory, student1);

        checkResult(
                "Sociology - Fourth Year",
                result1,
                findAllocatedBlock(dormitory, "HU101"),
                "Building I"
        );

        // TEST 2: Sociology - Second Year
        Student student2 = createStudent(
                "HU102", "Sociology",
                AcademicYear.SECOND_YEAR
        );

        boolean result2 =
                allocationService.allocateStudent(dormitory, student2);

        checkResult(
                "Sociology - Second Year",
                result2,
                findAllocatedBlock(dormitory, "HU102"),
                "Wing B"
        );

        // TEST 3: IT - Fourth Year
        Student student3 = createStudent(
                "HU103", "IT",
                AcademicYear.FOURTH_YEAR
        );

        boolean result3 =
                allocationService.allocateStudent(dormitory, student3);

        checkResult(
                "IT - Fourth Year",
                result3,
                findAllocatedBlock(dormitory, "HU103"),
                "Wing A"
        );

        // TEST 4: IT - Fifth Year
        Student student4 = createStudent(
                "HU104", "IT",
                AcademicYear.FIFTH_YEAR
        );

        boolean result4 =
                allocationService.allocateStudent(dormitory, student4);

        checkResult(
                "IT - Fifth Year",
                result4,
                findAllocatedBlock(dormitory, "HU104"),
                null
        );

        // TEST 5: Unlisted Department
        Student student5 = createStudent(
                "HU105", "Unlisted Department",
                AcademicYear.FRESHMAN
        );

        boolean result5 =
                allocationService.allocateStudent(dormitory, student5);

        checkResult(
                "Unlisted Department - Freshman",
                result5,
                findAllocatedBlock(dormitory, "HU105"),
                null
        );
    }

    private static Student createStudent(
            String id,
            String department,
            AcademicYear year) {

        return new Student(
                id,
                "Test Student " + id,
                "student@gmail.com",
                "0911111111",
                id,
                department,
                year,
                Gender.MALE,
                false
        );
    }

    private static String findAllocatedBlock(
            Dormitory dormitory,
            String studentId) {

        for (Block block : dormitory.getMaleSection().getBlocks()) {

            for (Room room : block.getRooms()) {

                for (Student student : room.getStudents()) {

                    if (student.getStudentId().equals(studentId)) {
                        return block.getBlockName();
                    }
                }
            }
        }

        return null;
    }

    private static void checkResult(
            String testName,
            boolean allocated,
            String actualBlock,
            String expectedBlock) {

        boolean passed;

        if (expectedBlock == null) {
            passed = !allocated && actualBlock == null;
        } else {
            passed = allocated
                    && expectedBlock.equalsIgnoreCase(actualBlock);
        }

        System.out.println("\n" + testName);

        System.out.println("Expected: "
                + (expectedBlock == null ? "REJECTED" : expectedBlock));

        System.out.println("Actual: "
                + (actualBlock == null ? "REJECTED" : actualBlock));

        System.out.println(passed ? "PASS" : "FAIL");
    }
}
