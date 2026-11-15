
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class MaleSpecialAccommodationTest {

    public static void main(String[] args) {

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        RoomAllocationService allocation =
                new RoomAllocationService();

        // TEST 1: Special male student.
        Student special = new Student(
                "MS001", "Special Male",
                "special@gmail.com", "0912345678",
                "MS001", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.MALE, true);

        boolean firstResult =
                allocation.allocateStudent(dormitory, special);

        Room specialRoom = findStudentRoom(dormitory, "MS001");

        System.out.println("\nTEST 1: Special accommodation");
        System.out.println("Allocation successful: " + firstResult);
        System.out.println("Assigned to Building I: "
                + (specialRoom != null));
        System.out.println("First-floor room: "
                + (specialRoom != null
                        && specialRoom.isFirstFloor()));

        // TEST 2: Regular eligible male student.
        Student regular = new Student(
                "MS002", "Regular Male",
                "regular@gmail.com", "0912345679",
                "MS002", "Sociology",
                AcademicYear.FOURTH_YEAR,
                Gender.MALE, false);

        boolean secondResult =
                allocation.allocateStudent(dormitory, regular);

        Room regularRoom = findStudentRoom(dormitory, "MS002");

        System.out.println("\nTEST 2: Regular eligible student");
        System.out.println("Allocation successful: " + secondResult);
        System.out.println("Assigned to Building I: "
                + (regularRoom != null
                        && regularRoom.getRoomNumber()
                                .startsWith("BuildingI")));

        System.out.println("Shares special student's room: "
                + (specialRoom != null
                        && regularRoom == specialRoom));

        // TEST 3: Fill all first-floor rooms in Building I.
        Block buildingI = findBlock(dormitory, "Building I");

        int filledRooms = 0;
        int studentNumber = 1;

        for (Room room : buildingI.getRooms()) {

            if (room.isFirstFloor()) {

                while (!room.isFull()) {

                    Student filler = new Student(
                            "MALE-FILL-" + studentNumber,
                            "Test Male",
                            "filler@gmail.com",
                            "0912345678",
                            "MALE-FILL-" + studentNumber,
                            "Sociology",
                            AcademicYear.FOURTH_YEAR,
                            Gender.MALE);

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

        // TEST 4: Another special male student.
        Student lastSpecial = new Student(
                "MS003", "Another Special Male",
                "another@gmail.com", "0912345680",
                "MS003", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.MALE, true);

        boolean lastResult =
                allocation.allocateStudent(dormitory, lastSpecial);

        System.out.println("\nTEST 4: No first-floor room available");
        System.out.println("Allocation rejected: " + !lastResult);
    }

    private static Block findBlock(
            Dormitory dormitory, String blockName) {

        for (Block block :
                dormitory.getMaleSection().getBlocks()) {

            if (block.getBlockName().equalsIgnoreCase(blockName)) {
                return block;
            }
        }

        return null;
    }

    private static Room findStudentRoom(
            Dormitory dormitory, String studentId) {

        for (Block block :
                dormitory.getMaleSection().getBlocks()) {

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

