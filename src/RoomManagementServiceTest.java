import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Block;
import com.smartdorm.enums.SectionType;
import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.service.RoomManagementService;

public class RoomManagementServiceTest {

    public static void main(String[] args) {

        DormitoryConfigurationService configurationService =
                new DormitoryConfigurationService();

        Dormitory dormitory =
                configurationService.configureDormitory();

        RoomManagementService roomService =
                new RoomManagementService();

        // Test 1: Display all buildings
        System.out.println("\nTEST 1: DISPLAY ALL BUILDINGS");
        roomService.displayAllBuildings(dormitory);

        // Test 2: Find a building
        System.out.println("\nTEST 2: FIND BUILDING");

        Block building = roomService.findBuilding(
                dormitory,
                SectionType.MALE,
                "Building I");

        if (building != null) {
            System.out.println("Building found: "
                    + building.getBlockName());
        } else {
            System.out.println("Building not found.");
        }

        // Test 3: Display rooms in a building
        System.out.println("\nTEST 3: DISPLAY BUILDING ROOMS");

        roomService.displayRoomsInBuilding(
                dormitory,
                SectionType.MALE,
                "Building I");

        // Test 4: Search for a room
        System.out.println("\nTEST 4: SEARCH ROOM");

        if (building != null && !building.getRooms().isEmpty()) {

            String roomNumber =
                    building.getRooms().get(0).getRoomNumber();

            if (roomService.searchRoom(
                    dormitory,
                    SectionType.MALE,
                    roomNumber) != null) {

                System.out.println("Room found: " + roomNumber);
            } else {
                System.out.println("Room not found.");
            }
        }

        System.out.println("\nRoom Management tests completed.");
    }
}