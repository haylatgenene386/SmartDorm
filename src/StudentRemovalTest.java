
import com.smartdorm.model.Room;
import com.smartdorm.model.Student;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class StudentRemovalTest {

    public static void main(String[] args) {

        Room room = new Room("TEST-001", 1);

        Student student1 = new Student(
                "R001", "Abebe Kebede",
                "abebe@gmail.com", "0911111111",
                "HU001", "IT",
                AcademicYear.THIRD_YEAR, Gender.MALE
        );

        Student student2 = new Student(
                "R002", "Kebede Ali",
                "kebede@gmail.com", "0922222222",
                "HU002", "CS",
                AcademicYear.SECOND_YEAR, Gender.MALE
        );

        // Add students
        room.addStudent(student1);
        room.addStudent(student2);

        System.out.println("===== BEFORE REMOVAL =====");
        System.out.println("Occupied Beds: "
                + (room.getCapacity() - room.getAvailableBeds()));
        System.out.println("Available Beds: "
                + room.getAvailableBeds());

        // Remove student
        boolean removed = room.removeStudent("HU001");

        System.out.println("\n===== AFTER REMOVAL =====");
        System.out.println("Removal Result: "
                + (removed ? "PASS" : "FAIL"));
        System.out.println("Occupied Beds: "
                + (room.getCapacity() - room.getAvailableBeds()));
        System.out.println("Available Beds: "
                + room.getAvailableBeds());

        // Test removing the same student again
        boolean removedAgain = room.removeStudent("HU001");

        System.out.println("\n===== VALIDATION =====");
        System.out.println("Student Removed Once: "
                + (removed ? "PASS" : "FAIL"));
        System.out.println("Duplicate Removal Prevention: "
                + (!removedAgain ? "PASS" : "FAIL"));
        System.out.println("Remaining Students: "
                + room.getStudents().size());
    }
}
