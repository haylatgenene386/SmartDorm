
import com.smartdorm.model.Room;
import com.smartdorm.model.Student;
import com.smartdorm.enums.Gender;
import com.smartdorm.enums.AcademicYear;

public class GenderSeparationTest {

    public static void main(String[] args) {

        Room room = new Room("GENDER-001", 1);

        Student maleStudent = new Student(
            "P001", "Male Student",
            "male@gmail.com", "0911111111",
            "HU001", "IT",
            AcademicYear.THIRD_YEAR,
            Gender.MALE, false
        );

        Student femaleStudent = new Student(
            "P002", "Female Student",
            "female@gmail.com", "0922222222",
            "HU002", "IT",
            AcademicYear.THIRD_YEAR,
            Gender.FEMALE, false
        );

        Student anotherMale = new Student(
            "P003", "Another Male Student",
            "another@gmail.com", "0933333333",
            "HU003", "IT",
            AcademicYear.THIRD_YEAR,
            Gender.MALE, false
        );

        System.out.println("===== GENDER SEPARATION TEST =====");

        boolean first = room.addStudent(maleStudent);
        boolean second = room.addStudent(femaleStudent);
        boolean third = room.addStudent(anotherMale);

        System.out.println("First male student: "
                + (first ? "ACCEPTED" : "REJECTED"));

        System.out.println("Female student in male room: "
                + (second ? "ACCEPTED" : "REJECTED"));

        System.out.println("Second male student: "
                + (third ? "ACCEPTED" : "REJECTED"));

        System.out.println("\nFinal Occupancy: "
                + room.getStudents().size());

        if (first && !second && third
                && room.getStudents().size() == 2) {

            System.out.println(
                "\nPASS: Gender separation is correctly enforced!"
            );

        } else {

            System.out.println(
                "\nFAIL: Gender separation test failed!"
            );
        }
    }
}
