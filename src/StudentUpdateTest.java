
import com.smartdorm.model.Student;
import com.smartdorm.service.StudentRegistrationService;
import com.smartdorm.service.StudentUpdateService;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class StudentUpdateTest {

    public static void main(String[] args) {

        // Create registration service
        StudentRegistrationService registration =
                new StudentRegistrationService();

        // Create update service
        StudentUpdateService updateService =
                new StudentUpdateService(registration);

        // Create and register a student
        Student student = new Student(
                "P001",
                "Abebe Kebede",
                "abebe@gmail.com",
                "0911000001",
                "HU001",
                "Information Technology",
                AcademicYear.SECOND_YEAR,
                Gender.MALE
        );

        registration.registerStudent(student);

        System.out.println("\n===== BEFORE UPDATE =====");
        student.displayInformation();

        // Update student information
        System.out.println("\n===== UPDATING STUDENT =====");

        boolean updated = updateService.updateStudent(
                "HU001",
                "Abebe Genene",
                "abebegenene@gmail.com",
                "0911222333",
                "Software Engineering",
                AcademicYear.THIRD_YEAR
        );

        System.out.println("Update successful: " + updated);

        // Display updated information
        System.out.println("\n===== AFTER UPDATE =====");
        student.displayInformation();

        // Test invalid student ID
        System.out.println("\n===== INVALID STUDENT TEST =====");

        boolean invalidUpdate = updateService.updateStudent(
                "HU999",
                "Unknown Student",
                "unknown@gmail.com",
                "0911999999",
                "Computer Science",
                AcademicYear.FOURTH_YEAR
        );

        System.out.println("Invalid student update accepted: "
                + invalidUpdate);

        // Final results
        System.out.println("\n===== FINAL RESULTS =====");

        System.out.println("Original Student ID: "
                + student.getStudentId());

        System.out.println("Updated Department: "
                + student.getDepartment());

        System.out.println("Updated Academic Year: "
                + student.getAcademicYear());

        System.out.println("Total Registered Students: "
                + registration.getTotalRegisteredStudents());
    }
}
