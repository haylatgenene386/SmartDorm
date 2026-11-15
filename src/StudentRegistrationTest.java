
import com.smartdorm.model.Student;
import com.smartdorm.service.StudentRegistrationService;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class StudentRegistrationTest {

    public static void main(String[] args) {

        StudentRegistrationService registration =
                new StudentRegistrationService();

        // Create students
        Student student1 = new Student(
                "R001", "Abebe Kebede",
                "abebe@gmail.com", "0911111111",
                "HU001", "Information Technology",
                AcademicYear.THIRD_YEAR,
                Gender.MALE
        );

        Student student2 = new Student(
                "R002", "Almaz Bekele",
                "almaz@gmail.com", "0922222222",
                "HU002", "Software Engineering",
                AcademicYear.SECOND_YEAR,
                Gender.FEMALE
        );

        // Register students
        System.out.println("===== STUDENT REGISTRATION TEST =====");

        boolean result1 = registration.registerStudent(student1);
        boolean result2 = registration.registerStudent(student2);

        // Attempt duplicate registration
        boolean result3 = registration.registerStudent(
                new Student(
                        "R003", "Duplicate Student",
                        "duplicate@gmail.com", "0933333333",
                        "HU001", "Computer Science",
                        AcademicYear.FRESHMAN,
                        Gender.MALE
                )
        );

        System.out.println("\n===== TEST RESULTS =====");

        System.out.println("First Registration: "
                + (result1 ? "PASS" : "FAIL"));

        System.out.println("Second Registration: "
                + (result2 ? "PASS" : "FAIL"));

        System.out.println("Duplicate Prevention: "
                + (!result3 ? "PASS" : "FAIL"));

        System.out.println("Total Registered Students: "
                + registration.getTotalRegisteredStudents());

        System.out.println("Find HU001: "
                + (registration.findStudentById("HU001") != null
                ? "PASS" : "FAIL"));
    }
}
