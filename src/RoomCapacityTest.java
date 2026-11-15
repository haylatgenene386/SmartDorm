
import com.smartdorm.model.Room;
import com.smartdorm.model.Student;
import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

public class RoomCapacityTest {

    public static void main(String[] args) {

        Room room = new Room("TEST-001", 1);

        System.out.println("===== ROOM CAPACITY TEST =====");

        for (int i = 1; i <= 7; i++) {

            Student student = new Student(
                "P" + i,
                "Student " + i,
                "student" + i + "@gmail.com",
                "0911111111",
                "HU" + i,
                "Information Technology",
                AcademicYear.THIRD_YEAR,
                Gender.MALE,
                false
            );

            boolean allocated = room.addStudent(student);

            System.out.println(
                "Student " + i + ": "
                + (allocated ? "ACCEPTED" : "REJECTED")
            );
        }

        System.out.println("\nFinal Room Occupancy: "
                + room.getStudents().size());

        System.out.println("Available Beds: "
                + room.getAvailableBeds());

        System.out.println("Room Full: " + room.isFull());

        if (room.getStudents().size() == 6
                && room.isFull()) {
            System.out.println("\nPASS: Room capacity is correctly enforced!");
        } else {
            System.out.println("\nFAIL: Room capacity test failed!");
        }
    }
}
