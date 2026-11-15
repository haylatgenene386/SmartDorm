
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class SpecialAccommodationFullTest {

    public static void main(String[] args) {

        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        Dormitory dormitory = config.configureDormitory();

        RoomAllocationService allocationService =
                new RoomAllocationService();

        Block buildingI = null;

        // Find Building I
        for (Block block : dormitory.getMaleSection().getBlocks()) {
            if (block.getBlockName().equalsIgnoreCase("Building I")) {
                buildingI = block;
                break;
            }
        }

        if (buildingI == null) {
            System.out.println("FAIL: Building I not found.");
            return;
        }

        int filledRooms = 0;

        // Fill every first-floor room in Building I
        for (Room room : buildingI.getRooms()) {

            if (room.isFirstFloor()) {

                for (int i = 1; i <= 6; i++) {

                    Student student = new Student(
                        "P" + filledRooms + "-" + i,
                        "Existing Student " + i,
                        "test@gmail.com",
                        "0911111111",
                        "HU" + filledRooms + "-" + i,
                        "Sociology",
                        AcademicYear.FOURTH_YEAR,
                        Gender.MALE,
                        false
                    );

                    room.addStudent(student);
                }

                filledRooms++;
            }
        }

        System.out.println("First-floor rooms filled: " + filledRooms);

        // Try to allocate a special-accommodation student
        Student specialStudent = new Student(
            "SPECIAL-001",
            "Priority Student",
            "priority@gmail.com",
            "0922222222",
            "HU-SPECIAL-001",
            "Unlisted Department",
            AcademicYear.FRESHMAN,
            Gender.MALE,
            true
        );

        boolean result =
                allocationService.allocateStudent(dormitory, specialStudent);

        if (!result) {
            System.out.println(
                "\nPASS: Student was not moved to another building."
            );
        } else {
            System.out.println(
                "\nFAIL: Student was allocated despite full priority rooms."
            );
        }
    }
}
