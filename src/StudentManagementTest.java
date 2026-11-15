
import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Student;
import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.service.StudentRegistrationService;
import com.smartdorm.service.RoomAllocationService;
import com.smartdorm.service.StudentManagementService;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class StudentManagementTest {

    public static void main(String[] args) {

        // Configure dormitory
        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        // Create services
        StudentRegistrationService registration =
                new StudentRegistrationService();

        RoomAllocationService allocation =
                new RoomAllocationService();

        StudentManagementService management =
                new StudentManagementService(registration, allocation);

        // Create two students
        Student male = new Student(
                "P001", "Allocated Male",
                "male@example.com", "0911000001",
                "HU001", "Sociology",
                AcademicYear.FOURTH_YEAR,
                Gender.MALE
        );

        Student female = new Student(
                "P002", "Unallocated Female",
                "female@example.com", "0911000002",
                "HU002", "Law",
                AcademicYear.FIFTH_YEAR,
                Gender.FEMALE
        );

        System.out.println("\n===== STUDENT MANAGEMENT TEST =====");

        // Register both students
        registration.registerStudent(male);
        registration.registerStudent(female);

        // Allocate only the male student
        boolean allocated =
                allocation.allocateStudent(dormitory, male);

        System.out.println("\nMale allocation successful: " + allocated);
        System.out.println("Registered students: "
                + registration.getTotalRegisteredStudents());
        System.out.println("Occupied beds: "
                + dormitory.getOccupiedBeds());

        // Remove allocated student
        System.out.println("\n--- Removing Allocated Student ---");

        boolean removedMale =
                management.removeStudent(dormitory, "HU001");

        System.out.println("Removal successful: " + removedMale);

        // Remove unallocated student
        System.out.println("\n--- Removing Unallocated Student ---");

        boolean removedFemale =
                management.removeStudent(dormitory, "HU002");

        System.out.println("Removal successful: " + removedFemale);

        // Try removing the same student again
        System.out.println("\n--- Testing Duplicate Removal ---");

        boolean duplicateRemoval =
                management.removeStudent(dormitory, "HU001");

        System.out.println("Duplicate removal accepted: "
                + duplicateRemoval);

        // Final results
        System.out.println("\n===== FINAL RESULTS =====");

        System.out.println("Registered students: "
                + registration.getTotalRegisteredStudents());

        System.out.println("Occupied beds: "
                + dormitory.getOccupiedBeds());

        System.out.println("Available beds: "
                + dormitory.getAvailableBeds());
    }
}
