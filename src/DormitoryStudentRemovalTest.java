
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class DormitoryStudentRemovalTest {

    public static void main(String[] args) {

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        RoomAllocationService allocation =
                new RoomAllocationService();

        Dormitory dormitory = config.configureDormitory();

        // Create one male and one female student
        Student male = new Student(
                "R001", "Abebe Kebede",
                "abebe@gmail.com", "0911111111",
                "HU001", "Sociology",
                AcademicYear.FOURTH_YEAR,
                Gender.MALE
        );

        Student female = new Student(
                "R002", "Almaz Bekele",
                "almaz@gmail.com", "0922222222",
                "HU002", "Law",
                AcademicYear.FIFTH_YEAR,
                Gender.FEMALE
        );

        // Allocate both students
        System.out.println("===== STUDENT ALLOCATION =====");

        boolean maleAllocated =
                allocation.allocateStudent(dormitory, male);

        boolean femaleAllocated =
                allocation.allocateStudent(dormitory, female);

        // Check occupancy before removal
        System.out.println("\n===== BEFORE REMOVAL =====");
        System.out.println("Occupied Beds: "
                + dormitory.getOccupiedBeds());
        System.out.println("Available Beds: "
                + dormitory.getAvailableBeds());

        // Remove male student
        System.out.println("\n===== REMOVE MALE STUDENT =====");

        boolean maleRemoved =
                allocation.removeStudentFromDormitory(
                        dormitory, "HU001");

        // Remove female student
        System.out.println("\n===== REMOVE FEMALE STUDENT =====");

        boolean femaleRemoved =
                allocation.removeStudentFromDormitory(
                        dormitory, "HU002");

        // Attempt duplicate removal
        boolean duplicateRemoval =
                allocation.removeStudentFromDormitory(
                        dormitory, "HU001");

        // Final results
        System.out.println("\n===== FINAL TEST RESULTS =====");

        System.out.println("Male Allocation: "
                + (maleAllocated ? "PASS" : "FAIL"));

        System.out.println("Female Allocation: "
                + (femaleAllocated ? "PASS" : "FAIL"));

        System.out.println("Male Removal: "
                + (maleRemoved ? "PASS" : "FAIL"));

        System.out.println("Female Removal: "
                + (femaleRemoved ? "PASS" : "FAIL"));

        System.out.println("Duplicate Removal Prevention: "
                + (!duplicateRemoval ? "PASS" : "FAIL"));

        System.out.println("Final Occupied Beds: "
                + dormitory.getOccupiedBeds());

        System.out.println("Final Available Beds: "
                + dormitory.getAvailableBeds());
    }
}
