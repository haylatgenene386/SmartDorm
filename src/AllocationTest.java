
import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Student;
import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.service.RoomAllocationService;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class AllocationTest {

    public static void main(String[] args) {

        DormitoryConfigurationService configurationService =
                new DormitoryConfigurationService();

        Dormitory dormitory =
                configurationService.configureDormitory();

        RoomAllocationService allocationService =
                new RoomAllocationService();

        System.out.println("\n===== STUDENT ALLOCATION TESTS =====");

        // TEST 1: Male student requiring special accommodation
        Student maleSpecial = new Student(
                "P001", "Male Special Student",
                "male@gmail.com", "0911111111",
                "HU001", "Unlisted Department",
                AcademicYear.FRESHMAN, Gender.MALE, true
        );

        allocationService.allocateStudent(dormitory, maleSpecial);

        // TEST 2: Regular male Sociology fourth-year student
        Student maleRegular = new Student(
                "P002", "Male Regular Student",
                "regular@gmail.com", "0922222222",
                "HU002", "Sociology",
                AcademicYear.FOURTH_YEAR, Gender.MALE, false
        );

        allocationService.allocateStudent(dormitory, maleRegular);

        // TEST 3: Female student requiring special accommodation
        Student femaleSpecial = new Student(
                "P003", "Female Special Student",
                "female@gmail.com", "0933333333",
                "HU003", "Unlisted Department",
                AcademicYear.FRESHMAN, Gender.FEMALE, true
        );

        allocationService.allocateStudent(dormitory, femaleSpecial);

        System.out.println("\n===== FINAL ALLOCATION TEST COMPLETED =====");

        dormitory.displayDormitoryInformation();
    }
}
