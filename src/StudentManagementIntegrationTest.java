
import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Student;
import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.service.StudentManagementController;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class StudentManagementIntegrationTest {

    public static void main(String[] args) {

        // Configure dormitory
        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        // Create central controller
        StudentManagementController controller =
                new StudentManagementController(dormitory);

        System.out.println("\n===== STUDENT MANAGEMENT INTEGRATION TEST =====");

        // Create students
        Student student1 = new Student(
                "P001", "Abebe Kebede",
                "abebe@gmail.com", "0911000001",
                "HU001", "Sociology",
                AcademicYear.FOURTH_YEAR, Gender.MALE
        );

        Student student2 = new Student(
                "P002", "Sara Mohammed",
                "sara@gmail.com", "0911000002",
                "HU002", "Law",
                AcademicYear.FIFTH_YEAR, Gender.FEMALE
        );

        // TEST 1: Register students
        System.out.println("\n--- TEST 1: REGISTRATION ---");

        boolean registered1 =
                controller.registerStudent(student1);

        boolean registered2 =
                controller.registerStudent(student2);

        System.out.println("First registration: " + registered1);
        System.out.println("Second registration: " + registered2);

        // TEST 2: Allocate rooms
        System.out.println("\n--- TEST 2: ROOM ALLOCATION ---");

        boolean allocated1 =
                controller.allocateStudent("HU001");

        boolean allocated2 =
                controller.allocateStudent("HU002");

        System.out.println("First allocation: " + allocated1);
        System.out.println("Second allocation: " + allocated2);

        // TEST 3: Update student
        System.out.println("\n--- TEST 3: UPDATE STUDENT ---");

        boolean updated = controller.updateStudent(
                "HU001",
                "Abebe Genene",
                "abebegenene@gmail.com",
                "0911222333",
                "Sociology",
                AcademicYear.FIFTH_YEAR
        );

        System.out.println("Update successful: " + updated);

        // TEST 4: Search student
        System.out.println("\n--- TEST 4: SEARCH STUDENT ---");

        Student found = controller.searchStudent("HU001");

        System.out.println("Student found: " + (found != null));

        // TEST 5: Display all students
        System.out.println("\n--- TEST 5: DISPLAY ALL STUDENTS ---");

        controller.displayAllStudents();

        // TEST 6: Display total students
        System.out.println("\n--- TEST 6: TOTAL STUDENTS ---");

        controller.displayTotalStudents();

        // TEST 7: Remove student
        System.out.println("\n--- TEST 7: REMOVE STUDENT ---");

        boolean removed = controller.removeStudent("HU001");

        System.out.println("Student removed: " + removed);

        // Final results
        System.out.println("\n===== FINAL SYSTEM STATUS =====");

        controller.displayTotalStudents();

        System.out.println("Occupied beds: "
                + dormitory.getOccupiedBeds());

        System.out.println("Available beds: "
                + dormitory.getAvailableBeds());
    }
}
