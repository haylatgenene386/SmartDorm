
import com.smartdorm.model.Student;
import com.smartdorm.service.StudentRegistrationService;
import com.smartdorm.service.StudentQueryService;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class StudentQueryTest {

    public static void main(String[] args) {

        // Create services
        StudentRegistrationService registration =
                new StudentRegistrationService();

        StudentQueryService queryService =
                new StudentQueryService(registration);

        // Create sample students
        Student student1 = new Student(
                "P001", "Abebe Kebede",
                "abebe@gmail.com", "0911000001",
                "HU001", "Information Technology",
                AcademicYear.SECOND_YEAR, Gender.MALE
        );

        Student student2 = new Student(
                "P002", "Sara Mohammed",
                "sara@gmail.com", "0911000002",
                "HU002", "Software Engineering",
                AcademicYear.THIRD_YEAR, Gender.FEMALE
        );

        Student student3 = new Student(
                "P003", "Abebe Genene",
                "abebeg@gmail.com", "0911000003",
                "HU003", "Computer Science",
                AcademicYear.FOURTH_YEAR, Gender.MALE
        );

        // Register students
        System.out.println("\n===== REGISTERING STUDENTS =====");

        registration.registerStudent(student1);
        registration.registerStudent(student2);
        registration.registerStudent(student3);

        // Test 1: Display all students
        System.out.println("\n===== TEST 1: DISPLAY ALL =====");

        queryService.displayAllStudents();

        // Test 2: Search by student ID
        System.out.println("\n===== TEST 2: SEARCH BY ID =====");

        Student found =
                queryService.searchByStudentId("HU002");

        System.out.println("Student found: " + (found != null));

        // Test 3: Search by name
        System.out.println("\n===== TEST 3: SEARCH BY NAME =====");

        queryService.searchByName("Abebe");

        // Test 4: Search unknown student
        System.out.println("\n===== TEST 4: UNKNOWN ID =====");

        Student missing =
                queryService.searchByStudentId("HU999");

        System.out.println("Unknown student found: "
                + (missing != null));

        // Test 5: Display total students
        System.out.println("\n===== TEST 5: TOTAL STUDENTS =====");

        queryService.displayTotalStudents();

        // Test 6: Empty registration list
        System.out.println("\n===== TEST 6: EMPTY LIST =====");

        StudentRegistrationService emptyRegistration =
                new StudentRegistrationService();

        StudentQueryService emptyQuery =
                new StudentQueryService(emptyRegistration);

        emptyQuery.displayAllStudents();
    }
}
