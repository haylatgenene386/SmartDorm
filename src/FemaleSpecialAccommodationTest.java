
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class FemaleSpecialAccommodationTest {

    public static void main(String[] args) {

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        RoomAllocationService allocation =
                new RoomAllocationService();

        // TEST 1: Special female student, regardless of eligibility.
        Student special = new Student(
                "FS001", "Special Female",
                "special@gmail.com", "0912345678",
                "FS001", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.FEMALE, true);

        boolean firstResult =
                allocation.allocateStudent(dormitory, special);

        Room specialRoom = findStudentRoom(dormitory, "FS001");

        System.out.println("\nTEST 1: Special accommodation");
        System.out.println("Allocation successful: " + firstResult);
        System.out.println("Assigned to Twins A: "
                + (specialRoom != null));
        System.out.println("First-floor room: "
                + (specialRoom != null
                        && specialRoom.isFirstFloor()));

        // TEST 2: Regular eligible female student.
        Student regular = new Student(
                "FS002", "Regular Female",
                "regular@gmail.com", "0912345679",
                "FS002", "Law",
                AcademicYear.FIFTH_YEAR,
                Gender.FEMALE, false);

        boolean secondResult =
                allocation.allocateStudent(dormitory, regular);

        Room regularRoom = findStudentRoom(dormitory, "FS002");

        System.out.println("\nTEST 2: Regular eligible student");
        System.out.println("Allocation successful: " + secondResult);
        System.out.println("Assigned to Twins A: "
                + (regularRoom != null
                        && regularRoom.getRoomNumber()
                                .startsWith("TwinsA")));

        System.out.println("Shares special student's room: "
                + (specialRoom != null
                        && regularRoom == specialRoom));

        // TEST 3: Fill all first-floor rooms in Twins A.
        Block twinsA = findBlock(dormitory, "Twins A");

        int filledRooms = 0;
        int studentNumber = 1;

        for (Room room : twinsA.getRooms()) {

            if (room.isFirstFloor()) {

                while (!room.isFull()) {

                    Student filler = new Student(
                            "FILL-" + studentNumber,
                            "Test Student",
                            "filler@gmail.com",
                            "0912345678",
                            "FILL-" + studentNumber,
                            "Law",
                            AcademicYear.FIFTH_YEAR,
                            Gender.FEMALE);

                    if (!room.addStudent(filler)) {
                        break;
                    }

                    studentNumber++;
                }

                if (room.isFull()) {
                    filledRooms++;
                }
            }
        }

        System.out.println("\nTEST 3: First-floor capacity");
        System.out.println("Full first-floor rooms: " + filledRooms);

        // TEST 4: Another special student must be rejected.
        Student lastSpecial = new Student(
                "FS003", "Another Special Female",
                "another@gmail.com", "0912345680",
                "FS003", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.FEMALE, true);

        boolean lastResult =
                allocation.allocateStudent(dormitory, lastSpecial);

        System.out.println("\nTEST 4: No first-floor room available");
        System.out.println("Allocation rejected: " + !lastResult);
    }

    private static Block findBlock(
            Dormitory dormitory, String blockName) {

        for (Block block :
                dormitory.getFemaleSection().getBlocks()) {

            if (block.getBlockName().equalsIgnoreCase(blockName)) {
                return block;
            }
        }

        return null;
    }

    private static Room findStudentRoom(
            Dormitory dormitory, String studentId) {

        for (Block block :
                dormitory.getFemaleSection().getBlocks()) {

            for (Room room : block.getRooms()) {

                for (Student student : room.getStudents()) {

                    if (student.getStudentId().equals(studentId)) {
                        return room;
                    }
                }
            }
        }

        return null;
    }
}
